package com.mpms.relatorioacessibilidadecortec.report;

import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

import java.math.BigInteger;
import java.util.List;

public class CreateWordList {

    static XWPFParagraph listPar;
    static XWPFRun listRun;

    static CTAbstractNum ctAbsNum;
    static XWPFAbstractNum xwpfAbsNum;
    static BigInteger numID;

    public static void listCreator(XWPFDocument mDoc, XWPFParagraph mParagraph, List<String> list) {
        XWPFNumbering numbering = mDoc.getNumbering();
        ctAbsNum = CTAbstractNum.Factory.newInstance();
        ctAbsNum.setAbstractNumId(findAbleId(numbering));
        setListStyle();
        xwpfAbsNum = new XWPFAbstractNum(ctAbsNum, numbering);
        numID = numbering.addNum(numbering.addAbstractNum(xwpfAbsNum));

        XmlCursor cursor = mParagraph.getCTP().newCursor();
        for (String string : list) {
            listPar = mDoc.createParagraph();
            listPar.getCTP().setPPr(mParagraph.getCTP().getPPr());
            listPar.setNumID(numID);
            listRun = listPar.createRun();
            listRun.setText(string);
            XmlCursor dispCursor = listPar.getCTP().newCursor();
            dispCursor.moveXml(cursor);
            dispCursor.close();
        }
    }

    private static BigInteger findAbleId(XWPFNumbering numbering) {
        BigInteger id = BigInteger.ZERO;
        boolean found = false;
        while (!found) {
            Object o = numbering.getAbstractNum(id);
            found = (o == null);
            if (!found)
                id = id.add(BigInteger.ONE);
        }
        return id;
    }

    private static void setListStyle() {
        CTLvl ctLvl = ctAbsNum.addNewLvl();
        ctLvl.setIlvl(BigInteger.ZERO);
        ctLvl.addNewNumFmt().setVal(STNumberFormat.LOWER_LETTER);
        ctLvl.addNewLvlText().setVal("%1)");
        ctLvl.addNewStart().setVal(BigInteger.ONE);
    }

}
