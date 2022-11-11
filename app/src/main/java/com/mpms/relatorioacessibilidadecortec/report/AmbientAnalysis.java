package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.commService.JsonCreation;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLevelSuffix;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmbientAnalysis implements StandardMeasurements {

    private CTAbstractNum ctAbsNum;

    private final XWPFDocument mDoc;
    private final XWPFParagraph mParagraph;

    public static final String ACCESS_TITLE = "Acessos e Circulações";

    public final Map<Integer, Integer> blockNumber = new HashMap<>();
    public static final Map<Integer, List<String>> sideIrregularities = new HashMap<>();
    public static final List<String> sideLocationList = new ArrayList<>();
    public static final List<String> placeType = new ArrayList<>();

    public AmbientAnalysis(XWPFDocument mDoc, XWPFParagraph mParagraph, JsonCreation jCreate) {
        this.mDoc = mDoc;
        this.mParagraph = mParagraph;
        blockCorrespondence(jCreate.getBlockList());
        SidewalkAnalysis.sidewalkVerification(jCreate.getSideList(), jCreate.getSideStRaList(), jCreate.getSideFlightList(),
                jCreate.getSidePhoneList(), jCreate.getSlopeList());
    }

    public void blockCorrespondence(List<BlockSpaceEntry> blockList) {
        for (int i = 0; i < blockList.size(); i++) {
            BlockSpaceEntry bEntry = blockList.get(i);
            if (bEntry.getBlockSpaceType() == 0)
                blockNumber.put(bEntry.getBlockSpaceID(), bEntry.getBlockSpaceNumber());
        }
    }

    public void irregularListing() throws XmlException {
        XWPFNumbering numbering = mDoc.getNumbering(); //Acessa o numbering.xml do arquivo .docx
        ctAbsNum = CTAbstractNum.Factory.newInstance(); //cria uma nova instância do CTAbstractNum (classe utilizada para acessar os abstractNums em arquivos XML)
        ctAbsNum.setAbstractNumId(findAbleId(numbering)); //Encontra um abstractNum livre e cria uma nova classe de regras para listas com esse abstractNum
        setListStyle(); //Configura a nova classe  de regras para listas (ver método abaixo)
        XWPFAbstractNum xwpfAbsNum = new XWPFAbstractNum(ctAbsNum, numbering); //Cria um novo XWPFAbstractNum usando o CTAbstractNum e o numbering.xml
        BigInteger numID = numbering.addNum(numbering.addAbstractNum(xwpfAbsNum)); //Adiciona o AbstractNum no numbering.xml e retorna este AbstractNum para o programa

        XmlCursor cursor = mParagraph.getCTP().newCursor(); //Crio um XmlCursor para movimentar pelo texto, adicionando textos após a inserção

        XWPFParagraph emptyPar = mDoc.createParagraph();
        XWPFRun emptyRun = emptyPar.createRun();
        emptyRun.setText("");

        for (String string : placeType) { //Iterador passando por toda a lista de string
            createIrregularList(string, numID, cursor, 0);

            for (int j = 0; j < sideLocationList.size(); j++) {
                String string2 = sideLocationList.get(j);
                createIrregularList(string2, numID, cursor, 1);

                List<String> irregular = sideIrregularities.get(j);
                if (irregular != null) {
                    for (String string3 : irregular)
                        createIrregularList(string3, numID, cursor, 2);
                }
            }
        }
    }

    /**
     * Este método é utilizado para encontrar qual é o último abstractNumId utilizado no numbering.xml
     * para definir regras de criação de listas. Ao encontrar o último, retorna um valor BigInteger que
     * está livre para criar uma nova regra de formatação de listas.
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

    /**
     * Este método tem como função estilizar a lista de irregularidades em seus três níveis. O primeiro nível
     * é a listagem em formato de letras, simples e deixando o texto em negrito. O segundo nível apresenta
     * os ambientes listados, demarcados com um bullet preenchido. Por fim, o terceiro nível apresenta as
     * irregularidades presentes em cada ambiente, com um bullet vazado
     */
    private void setListStyle() {
        CTLevelSuffix ct = CTLevelSuffix.Factory.newInstance();
        ct.setVal(STLevelSuffix.SPACE);

        CTLvl ctLvl = ctAbsNum.addNewLvl(); //Cria uma nova tag <w:lvl> dentro do abstractNumId
        ctLvl.setIlvl(BigInteger.ZERO); //Define o nível inicial da lista/numbering
        ctLvl.addNewNumFmt().setVal(STNumberFormat.LOWER_LETTER); //Define o tipo (formato) dos "números" dos itens da lista
        ctLvl.addNewLvlText().setVal("%1)"); // Define a formatação dos "números" dos itens (neste caso, a) por exemplo)
        ctLvl.setSuff(ct);
        ctLvl.addNewRPr().addNewB().setVal(true);
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

    /**
     * Método utilizado para criar a lista com as irregularidades dos ambientes em seus 3 níveis específicos.
     *
     * @param string o texto que será colocado no parágrafo
     * @param numID  BigInteger utilizado para definir a nova instância das regras de listagem criadas
     * @param cursor XmlCursor utilizado para movimentação do texto
     * @param p      inteiro que define em qual nível da lista o texto será inserido
     */
    private void createIrregularList(String string, BigInteger numID, XmlCursor cursor, int p) {
        XWPFParagraph listPar = mDoc.createParagraph(); //Cria um novo parágrafo a ser preenchido
        listPar.getCTP().setPPr(mParagraph.getCTP().getPPr()); //Pega a configuração do parágrafo base deste programa e coloca no parágrafo recém criado
        listPar.setFirstLineIndent(0); //Determina que não existe avanço da primeira linha comparada com as próximas dentro do parágrafo
        if (p == 1)
            listPar.setIndentationLeft(567); //Determina se a linha inteira possui avanço
        else if (p == 2)
            listPar.setIndentationLeft(750);
        listPar.setNumID(numID); //Cria a tag <w:numPr> (se não existir) e adiciona a tag filha <w:numId w:val="numID">
        if (p == 0)
            listPar.setNumILvl(BigInteger.ZERO); //Cria a tag <w:numPr> (se não existir) e adiciona a tag filha <w:ilvl w:val="BigInteger.ZERO">
        else if (p == 1)
            listPar.setNumILvl(BigInteger.ONE);
        else if (p == 2)
            listPar.setNumILvl(BigInteger.valueOf(2));
        XWPFRun listRun = listPar.createRun(); //Cria uma run neste parágrafo
        listRun.setText(string); //Adiciona o texto dentro da run (dentro da tag <w:t>)
        if (p == 0)
            listRun.setBold(true); //Faz o texto da run ficar em negrito
        XmlCursor dispCursor = listPar.getCTP().newCursor(); //Cria cursor de texto
        dispCursor.moveXml(cursor); //Movimenta o XML da posição do novo parágrafo para o primeiro cursor (fazendo o "texto andar")
        dispCursor.close(); //Fecha o cursor mais recente.
    }


}
