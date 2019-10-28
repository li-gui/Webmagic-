package com.ligui.pojo;

public class JobInfo {
    private String time;
    private String url;
    private String other;
    private String companyName;
    private String companyInfo;
    private String companyAddr;
    private String jobAddr;
    private String jobInfo;
    private String jobName;
    private String salaryMax;
    private String salaryMin;
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }



    @Override
    public String toString() {
        return "{ "+ '\n' +'\t'+"发布时间：" + '\t'+time + '\n' +
                "其他信息：｛"+other + '\t' +"}"+ '\n' +
                " 工作名称:" +'\t'+ jobName + '\n' +
                " 工资:" + '\t'+salaryMax + '\n' +
                " 公司名称:" +'\t'+ companyName +'\n' +
                " url:" +'\t'+ url + '\n' +
                " 公司信息:" +'\t'+ companyInfo + '\n' +
                " 公司地址:" + '\t'+companyAddr + '\n'+
                " 工作地区:" + '\t'+jobAddr + '\n' +
                jobInfo +'\n'+"}"+'\n';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }



}
