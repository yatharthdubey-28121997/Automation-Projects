package com.sib.salesforce.reporting;

import java.util.ArrayList;
import java.util.List;

public class ReportRunner {

    public static void main(String[] args) throws Exception {

        List<ReportingModel> items = new ArrayList<>();

        items.add(new ReportingModel(
                "Login Functionality",
                TestStatus.PASS,
                "User login successful"
        ));

        items.add(new ReportingModel(
                "Checkout Flow",
                TestStatus.FAIL,
                "Payment service returned error"
        ));

        items.add(new ReportingModel(
                "Profile Update",
                TestStatus.NOT_EXECUTED,
                "Blocked by environment issue"
        ));

        WordReportGenerator.generateReport(
                "Payment Platform",
                "QA",
                items,
                "AutomationReport.docx"
        );
    }
}