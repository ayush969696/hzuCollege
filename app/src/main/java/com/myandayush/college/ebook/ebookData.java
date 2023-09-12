package com.myandayush.college.ebook;

public class ebookData {
    private String pdfTitle, pdfUrl;

    public ebookData() {
    }

    public ebookData(String pdfTitle, String pdfUrl) {
        this.pdfTitle = pdfTitle;
        this.pdfUrl = pdfUrl;
    }

    public String getpdfTitle() {
        return pdfTitle;
    }

    public void setpdfTitle(String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
