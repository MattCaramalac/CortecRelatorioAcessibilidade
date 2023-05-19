package com.mpms.relatorioacessibilidadecortec.report;

import com.mpms.relatorioacessibilidadecortec.commService.JsonCreation;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.CirculationAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.ExtAccessAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.FountainAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.ParkingAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.PlaygroundAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.PoolAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.RestAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.RoomAnalysis;
import com.mpms.relatorioacessibilidadecortec.report.Ambients.SidewalkAnalysis;
import com.mpms.relatorioacessibilidadecortec.util.MapUtil;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

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

public class AmbientAnalysis implements StandardMeasurements, TagInterface {

    public static boolean err = false;

    private CTAbstractNum ctAbsNum;

    private final XWPFDocument mDoc;
    private final XWPFParagraph mParagraph;
    private final JsonCreation jCreate;

    public final Map<Integer, Integer> blockNumber = new HashMap<>();
    public static final Map<Integer, List<String>> sideIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> extIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> helpRoomIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> blockRoomIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> extParkIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> helpParkIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> helpRestIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> blockRestIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> helpFountainIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> blockFountainIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> playIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> poolIrregular = new HashMap<>();
    public static final Map<Integer, List<String>> circIrregular = new HashMap<>();
    public static final List<String> sideLocationList = new ArrayList<>();
    public static final List<String> extAccessList = new ArrayList<>();
    public static final List<String> helpRoomList = new ArrayList<>();
    public static final List<String> blockRoomList = new ArrayList<>();
    public static final List<String> extParkList = new ArrayList<>();
    public static final List<String> helpParkList = new ArrayList<>();
    public static final List<String> helpRestList = new ArrayList<>();
    public static final List<String> helpNoAccessRestList = new ArrayList<>();
    public static final List<String> blockRestList = new ArrayList<>();
    public static final List<String> blockNoAccessRestList = new ArrayList<>();
    public static final List<String> helpFountainList = new ArrayList<>();
    public static final List<String> blockFountainList = new ArrayList<>();
    public static final List<String> circList = new ArrayList<>();
    public static final List<String> playList = new ArrayList<>();
    public static final List<String> poolList = new ArrayList<>();
    public static final List<String> placeType = new ArrayList<>();

    public AmbientAnalysis(XWPFDocument mDoc, XWPFParagraph mParagraph, JsonCreation jCreate) {
        this.mDoc = mDoc;
        this.mParagraph = mParagraph;
        this.jCreate = jCreate;
        CirculationAnalysis.circVerification(jCreate.getCircList(), jCreate.getCircDoorList(), jCreate.getCircLockList(), jCreate.getCircSwitchList(),
                jCreate.getCircWindowList(), jCreate.getCircTableList(), jCreate.getCircBoardList(), jCreate.getCircFreeSpList(), jCreate.getCircStepList(),
                jCreate.getCircSlopeList(), jCreate.getCircFountainList(), jCreate.getCircEquipList(), jCreate.getCircCounterList(), jCreate.getCircProtectList(),
                jCreate.getCircRampStairsList(), jCreate.getCircFlightList(), jCreate.getCircRailList(), jCreate.getCircHandList());
        SidewalkAnalysis.sidewalkVerification(jCreate.getSideList(), jCreate.getSideStRaList(), jCreate.getSideFlightList(), jCreate.getSidePhoneList(),
                jCreate.getSlopeList());
        ExtAccessAnalysis.extAccessVerification(jCreate.getExtList(), jCreate.getGateLockList(), jCreate.getGateObsList(), jCreate.getExtPhoneList(),
                jCreate.getExtStRaList(), jCreate.getExtFlightList(), jCreate.getExtRailList(), jCreate.getExtHandList());
        ParkingAnalysis.parkVerification(jCreate.getBlockList(), jCreate.getParkList(), jCreate.getElderList(), jCreate.getPcdList(), jCreate.getParkStRaList(),
                jCreate.getParkFlightList(), jCreate.getParkRailList(), jCreate.getParkHandList());
        PlaygroundAnalysis.playVerification(jCreate.getPlayList());
        PoolAnalysis.poolVerification(jCreate.getpList(), jCreate.getPoolRampList(), jCreate.getPoolStairsList(), jCreate.getPoolBenchList(), jCreate.getPoolEquipList());
        RoomAnalysis.roomVerification(jCreate.getBlockList(), jCreate.getRoomList(), jCreate.getDoorList(), jCreate.getDoorLockList(), jCreate.getSwitchList(),
                jCreate.getWindowList(), jCreate.getTableList(), jCreate.getBoardList(), jCreate.getFreeList(), jCreate.getRoomStRaList(), jCreate.getRoomFlightList(),
                jCreate.getRoomRailList(), jCreate.getRoomHandList(), jCreate.getCounterList(), jCreate.getRoomWater(), jCreate.getEquipList());
        RestAnalysis.restVerification(jCreate.getBlockList(), jCreate.getRestList(), jCreate.getRestDoorList(), jCreate.getRestFrSpaceList(), jCreate.getBoxList(),
                jCreate.getBoxDoorList());
        checkHelpAreaHeader();
        blockCorrespondence(jCreate.getBlockList());

    }


    public static void checkHasAccessHeader() {
        err = true;
        for (int i = 0; i < AmbientAnalysis.placeType.size(); i++) {
            if (AmbientAnalysis.placeType.get(i).equals(ACCESS_TITLE))
                return;
        }
        AmbientAnalysis.placeType.add(ACCESS_TITLE);
    }

    public static void checkHelpAreaHeader() {
        err = true;
        for (int i = 0; i < AmbientAnalysis.placeType.size(); i++) {
            if (AmbientAnalysis.placeType.get(i).equals(HELP_TITLE))
                return;
        }
        AmbientAnalysis.placeType.add(HELP_TITLE);
    }

    public static void checkBlockHeader(int n) {
        AmbientAnalysis.placeType.add(BLOCK_TITLE + " " + n);
    }

    public void blockCorrespondence(List<BlockSpaceEntry> blockList) {
        for (BlockSpaceEntry bEntry : blockList) {
            if (bEntry.getBlockSpaceType() == 0)
                blockNumber.put(bEntry.getBlockSpaceID(), bEntry.getBlockSpaceNumber());
        }
        Map<Integer, Integer> blockOrder = MapUtil.sortByValue(blockNumber);
        for (Integer bNumber : blockOrder.values()) {
            checkBlockHeader(bNumber);
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

        int i = 0;
        int j = 1;
        for (String string : placeType) { //Iterador passando por toda a lista de string
            createIrregularList(string, numID, cursor, 0);

            if (i == 0) {
                irregularTextListing(circList, circIrregular, numID, cursor);
                irregularTextListing(sideLocationList, sideIrregular, numID, cursor);
                irregularTextListing(extAccessList, extIrregular, numID, cursor);
                irregularTextListing(extParkList, extParkIrregular, numID, cursor);
            } else if (i == 1) {
                irregularTextListing(playList, playIrregular, numID, cursor);
                irregularTextListing(poolList, poolIrregular, numID, cursor);
                irregularTextListing(helpRoomList, helpRoomIrregular, numID, cursor);
                irregularTextListing(helpParkList, helpParkIrregular, numID, cursor);
                irregularTextListing(helpRestList, helpRestIrregular, numID, cursor);
                if (helpNoAccessRestList.size() != 0)
                    irregularTextListing(helpNoAccessRestList, null, numID, cursor);
            } else {
                blockRoomList.clear();
                blockRoomIrregular.clear();
                blockRestList.clear();
                blockRestIrregular.clear();
                blockFountainList.clear();
                blockFountainIrregular.clear();
                for (Map.Entry<Integer, Integer> blNum : blockNumber.entrySet()) {
                    Integer blockID = blNum.getKey();
                    Integer blockNum = blNum.getValue();
                    if (blockNum == j) {
                        RoomAnalysis.blockRoomVerification(blockID, jCreate.getRoomList(), jCreate.getDoorList(), jCreate.getDoorLockList(), jCreate.getSwitchList(),
                                jCreate.getWindowList(), jCreate.getTableList(), jCreate.getBoardList(), jCreate.getFreeList(), jCreate.getRoomStRaList(), jCreate.getRoomFlightList(),
                                jCreate.getRoomRailList(), jCreate.getRoomHandList(), jCreate.getCounterList(), jCreate.getRoomWater(), jCreate.getEquipList());
                        if (blockRoomList.size() == 0 && blockRoomIrregular.size() == 0)
                            blockRoomList.add("Este bloco não possui salas com irregularidades");
                        else
                            irregularTextListing(blockRoomList, blockRoomIrregular, numID, cursor);


                        RestAnalysis.blockRestVerification(blockID, jCreate.getRestList(), jCreate.getRestDoorList(), jCreate.getRestFrSpaceList(), jCreate.getBoxList(),
                                jCreate.getBoxDoorList());
                        if (blockRestList.size() == 0 && blockRestIrregular.size() == 0)
                            blockRestList.add("Este bloco não possui sanitários com irregularidades");
                        else if (blockNoAccessRestList.size() != 0)
                            irregularTextListing(blockNoAccessRestList, null, numID, cursor);
                        irregularTextListing(blockRestList, blockRestIrregular, numID, cursor);


                        FountainAnalysis.blockFountainVerification(blockID, jCreate.getFountList());
                        if (blockFountainList.size() == 0 && blockFountainIrregular.size() == 0)
                            blockFountainList.add("Este bloco não possui bebedouros com irregularidades");
                        else
                            irregularTextListing(blockFountainList, blockFountainIrregular, numID, cursor);
                    }
                }
                j++;
            }
            i++;
        }
    }

    private void irregularTextListing(List<String> list, Map<Integer, List<String>> map, BigInteger numID, XmlCursor cursor) {
        for (int j = 0; j < list.size(); j++) {
            String string2 = list.get(j);
            createIrregularList(string2, numID, cursor, 1);

            if (map != null) {
                List<String> irregular = map.get(j);
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
