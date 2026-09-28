package com.sib.salesforce.reporting;

public class ReportingModel {

	private String item;
    private TestStatus status;
    private String comments;

    public ReportingModel(String item, TestStatus status, String comments) {
        this.item = item;
        this.status = status;
        this.comments = comments;
    }

    public String getItem() {
        return item;
    }

    public TestStatus getStatus() {
        return status;
    }

    public String getComments() {
        return comments;
    }
}
