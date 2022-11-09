package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.commService.JsonCreation;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;

import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelSuffix;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLevelSuffix;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmbientAnalysis implements StandardMeasurements {

    private CTAbstractNum ctAbsNum;

    private XWPFDocument mDoc;
    private XWPFParagraph mParagraph;
    private CTNumPr numPr = CTNumPr.Factory.newInstance();

    private final String ACCESS_TITLE = "Acessos e Circulações";

    private final Map<Integer, Integer> blockNumber = new HashMap<>();
    private final Map<Integer, List<String>> sideIrregularities = new HashMap<>();

    private final List<String> placeType = new ArrayList<>();
    private final List<String> sideLocationList = new ArrayList<>();

    public AmbientAnalysis(XWPFDocument mDoc, XWPFParagraph mParagraph, JsonCreation jCreate) {
        this.mDoc = mDoc;
        this.mParagraph = mParagraph;
        blockCorrespondence(jCreate.getBlockList());
        sidewalkAnalysis(jCreate.getSideList());
    }

    public void blockCorrespondence(List<BlockSpaceEntry> blockList) {
        for (int i = 0; i < blockList.size(); i++) {
            BlockSpaceEntry bEntry = blockList.get(i);
            if (bEntry.getBlockSpaceType() == 0)
                blockNumber.put(bEntry.getBlockSpaceID(), bEntry.getBlockSpaceNumber());
        }
    }

    public void sidewalkAnalysis(List<SidewalkEntry> sideList) {
        for (int i = 0; i < sideList.size(); i++) {
            List<String> sideIrregular = new ArrayList<>();
            boolean err = false;
            SidewalkEntry sideEntry = sideList.get(i);
            if (sideEntry.getStreetPavement() == 1) {
                if (sideEntry.getSidewalkWidth() < sideWidth) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("Largura da calçada inferior à 1,90 m;");
                }
                if (sideEntry.getSideFreeSpaceWidth() < freeLaneWidth) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("Largura da Faixa Livre da Calçada inferior à 1,20 m;");
                }
            }
            if (sideEntry.getSideTransSlope1() != null && sideEntry.getSideTransSlope1() > 3.0 ||
                    sideEntry.getSideTransSlope2() != null && sideEntry.getSideTransSlope2() > 3.0 ||
                    sideEntry.getSideTransSlope3() != null && sideEntry.getSideTransSlope3() > 3.0 ||
                    sideEntry.getSideTransSlope4() != null && sideEntry.getSideTransSlope4() > 3.0 ||
                    sideEntry.getSideTransSlope5() != null && sideEntry.getSideTransSlope5() > 3.0 ||
                    sideEntry.getSideTransSlope6() != null && sideEntry.getSideTransSlope6() > 3.0) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add("A inclinação transversal da superfície ultrapassa o valor máximo permitido de 3%;");
            }
            if (sideEntry.getHasSpecialFloor() == 1) {
                if (sideEntry.getSpecialFloorRightColor() == 0) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("O piso tátil não possui cor suficientemente contrastante em relação ao piso;");
                }

                if (sideEntry.getSpecialTileDirectionWidth() < minDirTactWidth) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("O piso tátil direcional possui largura inferior à 0,25 m;");
                } else if (sideEntry.getSpecialTileDirectionWidth() > maxDirTactWidth) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add("O piso tátil direcional possui largura superior à 0,40 m;");
                }

                if (sideEntry.getSpecialFloorObs() != null) {
                    checkHasSideHeader();
                    err = true;
                    sideIrregular.add(sideEntry.getSpecialFloorObs() + ";");
                }
            }

            if (sideEntry.getSideConStatus() == 0) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add(sideEntry.getSideConsObs() + ";");
            }

            if (sideEntry.getSideFloorIsAccessible() == 0) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add("O piso não pode ser considerado acessível, pois " + sideEntry.getAccessFloorObs() + ";");
            }

            if (sideEntry.getSidewalkHasLids() == 1) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add(sideEntry.getSidewalkLidDesc() + ";");
            }

            if (sideEntry.getHasAerialObstacle() == 1) {
                checkHasSideHeader();
                err = true;
                sideIrregular.add("Os seguintes obstáculos aéreos: " + sideEntry.getAerialObstacleDesc() + ";");
            }

//            TODO - Inserir os telefones, rampas, etc. etc.
            if (err)
                sideIrregularities.put(i, sideIrregular);
            sideLocationList.add("Calçada, ao longo da " + sideEntry.getSidewalkLocation() + ", com:");
        }
    }

    public void checkHasSideHeader() {
        for (int i = 0; i < placeType.size(); i++) {
            if (placeType.get(i).equals(ACCESS_TITLE))
                return;
        }
        placeType.add(ACCESS_TITLE);
    }

    public void writeTesting() throws XmlException {
        XWPFNumbering numbering = mDoc.getNumbering(); //Acessa o numbering.xml do arquivo .docx
        ctAbsNum = CTAbstractNum.Factory.newInstance(); //cria uma nova instância do CTAbstractNum (classe utilizada para acessar os abstractNums em arquivos XML)
        ctAbsNum.setAbstractNumId(findAbleId(numbering)); //Encontra um abstractNum livre e cria uma nova classe de regras para listas com esse abstractNum
        setListStyle(); //Configura a nova classe  de regras para listas (ver método abaixo)
        XWPFAbstractNum xwpfAbsNum = new XWPFAbstractNum(ctAbsNum, numbering); //Cria um novo XWPFAbstractNum usando o CTAbstractNum e o numbering.xml
        BigInteger numID = numbering.addNum(numbering.addAbstractNum(xwpfAbsNum)); //Adiciona o AbstractNum no numbering.xml e retorna este AbstractNum para o programa

        XmlCursor cursor = mParagraph.getCTP().newCursor(); //Crio um XmlCursor para movimentar pelo texto, adicionando textos após a inserção
        for (String string : placeType) { //Iterador passando por toda a lista de string
            XWPFParagraph listPar = mDoc.createParagraph(); //Cria um novo parágrafo a ser preenchido
            listPar.getCTP().setPPr(mParagraph.getCTP().getPPr()); //Pega a configuração do parágrafo base deste programa e coloca no parágrafo recém criado
            listPar.setFirstLineIndent(0); //Determina que não existe avanço da primeira linha comparada com as próximas dentro do parágrafo
            listPar.setNumID(numID); //Cria a tag <w:numPr> (se não existir) e adiciona a tag filha <w:numId w:val="numID">
            listPar.setNumILvl(BigInteger.ZERO); //Cria a tag <w:numPr> (se não existir) e adiciona a tag filha <w:ilvl w:val="BigInteger.ZERO">
            XWPFRun listRun = listPar.createRun(); //Cria uma run neste parágrafo
            listRun.setText(string); //Adiciona o texto dentro da run (dentro da tag <w:t>)
            listRun.setBold(true); //Faz o texto da run ficar em negrito
            XmlCursor dispCursor = listPar.getCTP().newCursor(); //Cria cursor de texto
            dispCursor.moveXml(cursor); //Movimenta o XML da posição do novo parágrafo para o primeiro cursor (fazendo o "texto andar")
            dispCursor.close(); //Fecha o cursor mais recente.
            for (String string2 : sideLocationList) {
                XWPFParagraph listPar2 = mDoc.createParagraph();
                listPar2.getCTP().setPPr(mParagraph.getCTP().getPPr());
                listPar2.setFirstLineIndent(0);
                listPar2.setIndentationLeft(567);
                listPar2.setNumID(numID);
                listPar2.setNumILvl(BigInteger.ONE);
                XWPFRun listRun2 = listPar2.createRun();
                listRun2.setText(string2);
                XmlCursor dispCursor2 = listPar2.getCTP().newCursor();
                dispCursor2.moveXml(cursor);
                dispCursor2.close();
                for (int i = 0; i < sideIrregularities.size(); i++) {
                    List<String> irregular = sideIrregularities.get(i);
                    if (irregular != null) {
                        for (String string3 : irregular) {
                            XWPFParagraph listPar3 = mDoc.createParagraph();
                            listPar3.getCTP().setPPr(mParagraph.getCTP().getPPr());
                            listPar3.setFirstLineIndent(0);
                            listPar3.setIndentationLeft(750);
                            listPar3.setNumID(numID);
                            listPar3.setNumILvl(BigInteger.valueOf(2));
                            XWPFRun listRun3 = listPar3.createRun();
                            listRun3.setText(string3);
                            XmlCursor dispCursor3 = listPar3.getCTP().newCursor();
                            dispCursor3.moveXml(cursor);
                            dispCursor3.close();
                        }
                    }
                }
            }
        }
    }

    /*Este método é utilizado para encontrar qual é o último abstractNumId utilizado no numbering.xml
    para definir regras de criação de listas. Ao encontrar o último, retorna um valor BigInteger que
    está livre para criar uma nova regra de formatação de listas.
     */
    private BigInteger findAbleId(XWPFNumbering numbering) {
        BigInteger id = BigInteger.ZERO; //O método inicia com 0 porque as definições de abstractNumId iniciam com este valor
        boolean found = false; //Seta valor como falso para garantir que acessa o método uma vez (trocar por do/while?)
        while (!found) {
            Object o = numbering.getAbstractNum(id); //Cria um objeto o que busca se existe um abstractNumID com o valor de id
            found = (o == null); //Se existe um objeto com abstractNumId com valor id, ele não retorna nulo, portanto found = falso
            if (!found)
                id = id.add(BigInteger.ONE); //Adiciona 1 para repetir o processo, até encontrar um abtractNumID disponível
        }
        return id;
    }

    private void setListStyle() {
        CTLevelSuffix ct = CTLevelSuffix.Factory.newInstance();
        ct.setVal(STLevelSuffix.SPACE);

        CTLvl ctLvl = ctAbsNum.addNewLvl(); //Cria uma nova tag <w:lvl> dentro do abstractNumId
        ctLvl.setIlvl(BigInteger.ZERO); //Define o nível inicial da lista/numbering
        ctLvl.addNewNumFmt().setVal(STNumberFormat.LOWER_LETTER); //Define o tipo (formato) dos "números" dos itens da lista
        ctLvl.addNewLvlText().setVal("%1)"); // Define a formatação dos "números" dos itens (neste caso, a) por exemplo)
        ctLvl.setSuff(ct);
        ctLvl.addNewStart().setVal(BigInteger.ONE); //Define qual é o valor equivalente para iniciar a listagem (a)

        ctLvl = ctAbsNum.addNewLvl();
        ctLvl.setIlvl(BigInteger.ONE);
        ctLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
        ctLvl.addNewLvlText().setVal("\u2022");
        ctLvl.setSuff(ct);
        ctLvl.addNewStart().setVal(BigInteger.ONE);

        ctLvl = ctAbsNum.addNewLvl();
        ctLvl.setIlvl(BigInteger.valueOf(2));
        ctLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
        ctLvl.addNewLvlText().setVal("\u25E6");
        ctLvl.setSuff(ct);
        ctLvl.addNewStart().setVal(BigInteger.ONE);
    }
}
