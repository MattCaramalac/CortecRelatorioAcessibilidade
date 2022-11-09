package com.mpms.relatorioacessibilidadecortec.report;

public interface StandardMeasurements {

//    Irregularities List "Style" - obtained through an exemplar .docx file

    String IRR_LIST = "<w:abstractNum w15:restartNumberingAfterBreak=\"0\" w:abstractNumId=\"0\"><w:nsid w:val=\"5C864C1F\"/>" +
            "<w:multiLevelType w:val=\"hybridMultilevel\"/><w:tmpl w:val=\"EE0A80B0\"/><w:lvl w:tplc=\"D11A823A\" w:ilvl=\"0\"><w:start w:val=\"1\"/>" +
            "<w:numFmt w:val=\"lowerLetter\"/><w:lvlText w:val=\"%1)\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:hanging=\"360\" w:left=\"1440\"/>" +
            "</w:pPr><w:rPr><w:rFonts w:hint=\"default\" w:cs=\"Times New Roman\" w:hAnsi=\"Times New Roman\" w:ascii=\"Times New Roman\"/>" +
            "<w:b/><w:bCs/><w:sz w:val=\"24\"/><w:szCs w:val=\"24\"/></w:rPr></w:lvl><w:lvl w:tplc=\"04160001\" w:ilvl=\"1\"><w:start w:val=\"1\"/>" +
            "<w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"\uF0B7\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:hanging=\"360\" w:left=\"2160\"/>" +
            "</w:pPr><w:rPr><w:rFonts w:hint=\"default\" w:hAnsi=\"Symbol\" w:ascii=\"Symbol\"/></w:rPr></w:lvl><w:lvl w:tplc=\"04160003\" w:ilvl=\"2\"><w:start w:val=\"1\"/>" +
            "<w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"o\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:hanging=\"180\" w:left=\"2880\"/>" +
            "</w:pPr><w:rPr><w:rFonts w:hint=\"default\" w:cs=\"Courier New\" w:hAnsi=\"Courier New\" w:ascii=\"Courier New\"/></w:rPr></w:lvl>" +
            "<w:lvl w:tplc=\"0416000F\" w:ilvl=\"3\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%4.\"/>" +
            "<w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:hanging=\"360\" w:left=\"3600\"/></w:pPr></w:lvl><w:lvl w:tplc=\"04160019\" w:ilvl=\"4\" w:tentative=\"1\">" +
            "<w:start w:val=\"1\"/><w:numFmt w:val=\"lowerLetter\"/><w:lvlText w:val=\"%5.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:hanging=\"360\" w:left=\"4320\"/>" +
            "</w:pPr></w:lvl><w:lvl w:tplc=\"0416001B\" w:ilvl=\"5\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"lowerRoman\"/><w:lvlText w:val=\"%6.\"/>" +
            "<w:lvlJc w:val=\"right\"/><w:pPr><w:ind w:hanging=\"180\" w:left=\"5040\"/></w:pPr></w:lvl><w:lvl w:tplc=\"0416000F\" w:ilvl=\"6\" w:tentative=\"1\">" +
            "<w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%7.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:hanging=\"360\" w:left=\"5760\"/>" +
            "</w:pPr></w:lvl><w:lvl w:tplc=\"04160019\" w:ilvl=\"7\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"lowerLetter\"/><w:lvlText w:val=\"%8.\"/>" +
            "<w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:hanging=\"360\" w:left=\"6480\"/></w:pPr></w:lvl><w:lvl w:tplc=\"0416001B\" w:ilvl=\"8\" w:tentative=\"1\">" +
            "<w:start w:val=\"1\"/><w:numFmt w:val=\"lowerRoman\"/><w:lvlText w:val=\"%9.\"/><w:lvlJc w:val=\"right\"/><w:pPr><w:ind w:hanging=\"180\" w:left=\"7200\"/>" +
            "</w:pPr></w:lvl></w:abstractNum>";

//    Sidewalk Measurements
    double sideWidth = 1.9;
    double freeLaneWidth = 1.2;
    double transSlope = 3; //Valor em %
    double minDirTactWidth = 0.25;
    double maxDirTactWidth = 0.4;
}
