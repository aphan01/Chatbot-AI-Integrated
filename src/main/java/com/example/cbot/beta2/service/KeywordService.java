package com.example.cbot.beta2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class KeywordService {

    private static final Logger logger = LoggerFactory.getLogger(KeywordService.class);

    private static final Map<String, Map<String, String>> keywordButtonMap = new HashMap<>();
    private static final Map<String, String> keywordLinkMap = new HashMap<>();

    static {
        // Button configurations
        Map<String, String> dashboardOptions = new HashMap<>();
        dashboardOptions.put("SPro", "https://demo.spro.eiss.vn/s-pro/dashboard/view/default");
        dashboardOptions.put("Clone", "https://demo.spro.eiss.vn/s-pro/dashboard/view/6644a4c31507442a5b68110a");
        dashboardOptions.put("Dashboard Management", "https://demo.spro.eiss.vn/s-pro/dashboard/manage");
        keywordButtonMap.put("dashboard", dashboardOptions);

        Map<String, String> myItemsOptions = new HashMap<>();
        myItemsOptions.put("Assigned to Me", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/assignToMe");
        myItemsOptions.put("Created by Me", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/approve");
        myItemsOptions.put("Following", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/following");
        myItemsOptions.put("Informed", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/informed");
        myItemsOptions.put("Drafts", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/draft");
        keywordButtonMap.put("my items", myItemsOptions);

        Map<String, String> monitorOptions = new HashMap<>();
        monitorOptions.put("Department", "https://demo.spro.eiss.vn/secure/ResourceAllocation.jspa?viewReportType=department");
        monitorOptions.put("Category", "https://demo.spro.eiss.vn/secure/ResourceAllocation.jspa?viewReportType=category");
        keywordButtonMap.put("monitor", monitorOptions);

        Map<String, String> requestOptions = new HashMap<>();
        requestOptions.put("Hướng dẫn sử dụng", "https://demo.spro.eiss.vn/plugins/servlet/ssc01-filedownload?guideName=HƯỚNG%20DẪN%20SỬ%20DỤNG.pdf&quotation=1");
        requestOptions.put("Xin phép nghỉ", "https://demo.spro.eiss.vn/secure/SSC01CreateTicket.jspa?processId=457&catalogId=1&issueKeys=");
        requestOptions.put("Xin thôi việc", "https://demo.spro.eiss.vn/secure/SSC01CreateTicket.jspa?processId=462&catalogId=1&issueKeys=");
        keywordButtonMap.put("Create a request", requestOptions);
        keywordButtonMap.put("request", requestOptions);

        // Link configurations
        keywordLinkMap.put("dashboard management", "https://demo.spro.eiss.vn/s-pro/dashboard/manage");
        keywordLinkMap.put("fpt spro", "https://demo.spro.eiss.vn/s-pro/dashboard/view/default");
        keywordLinkMap.put("clone", "https://demo.spro.eiss.vn/s-pro/dashboard/view/6644a4c31507442a5b68110a");
        keywordLinkMap.put("assigned to me", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/assignToMe");
        keywordLinkMap.put("created by me", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/approve");
        keywordLinkMap.put("following", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/following");
        keywordLinkMap.put("informed", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/informed");
        keywordLinkMap.put("drafts", "https://demo.spro.eiss.vn/s-pro/workflow/ticket/draft");
        keywordLinkMap.put("department", "https://demo.spro.eiss.vn/secure/ResourceAllocation.jspa?viewReportType=department");
        keywordLinkMap.put("category", "https://demo.spro.eiss.vn/secure/ResourceAllocation.jspa?viewReportType=category");
        keywordLinkMap.put("hướng dẫn sử dụng", "https://demo.spro.eiss.vn/plugins/servlet/ssc01-filedownload?guideName=HƯỚNG%20DẪN%20SỬ%20DỤNG.pdf&quotation=1");
        keywordLinkMap.put("xin phép nghỉ", "https://demo.spro.eiss.vn/secure/SSC01CreateTicket.jspa?processId=457&catalogId=1&issueKeys=");
        keywordLinkMap.put("xin thôi việc", "https://demo.spro.eiss.vn/secure/SSC01CreateTicket.jspa?processId=462&catalogId=1&issueKeys=");
    }

    public Map<String, String> getButtonOptionsForKeyword(String keyword) {
        Map<String, String> options = keywordButtonMap.get(keyword);
        logger.info("Retrieving button options for keyword '{}': {}", keyword, options);
        return options;
    }

    public String getLinkForKeyword(String keyword) {
        String link = keywordLinkMap.get(keyword);
        logger.info("Retrieving link for keyword '{}': {}", keyword, link);
        return link;
    }

    public Set<String> getKeywords() {
        return keywordButtonMap.keySet();
    }
}
