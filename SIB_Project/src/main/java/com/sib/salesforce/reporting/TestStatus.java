package com.sib.salesforce.reporting;

public enum TestStatus {

	PASS("PASS", "C6EFCE", "006100"),
    FAIL("FAIL", "FFC7CE", "9C0006"),
    NOT_EXECUTED("NOT EXECUTED", "FFEB9C", "9C6500");

    private String label;
    private String backgroundColor;
    private String textColor;

    TestStatus(String label, String backgroundColor, String textColor) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public String getLabel() {
        return label;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }
}
