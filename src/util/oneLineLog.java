package util;

public class oneLineLog {

    protected static String oneline;

    String timeStampUTC;			 // 1
    String reguestProsessingTime;            // 2
    String IP; 				 // 3
    String cacheResultCode;			 // 4
    String contentGatewayLengt;              // 5
    String clientRequestMethod;              // 6
    String proxyHierarchyRoute;              // 7
    String clientUserName; 			 // 8
    String serverName; 			 // 9

    public static String getOneline() {
        return oneline;
    }

    public static void setOneline(String oneline) {
        oneLineLog.oneline = oneline;
    }

    public String getTimeStampUTC() {
        return timeStampUTC;
    }

    public void setTimeStampUTC(String timeStampUTC) {
        this.timeStampUTC = timeStampUTC;
    }

    public String getContentGatewayLengt() {
        return contentGatewayLengt;
    }

    public void setContentGatewayLengt(String contentGatewayLengt) {
        this.contentGatewayLengt = contentGatewayLengt;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    String proxyResponseContentType; // 10

    String clientRequestDate;
    String clientRequestTime;

    public oneLineLog() {
        super();
    }

    public oneLineLog(String timeStampUTC, String reguestProsessingTime, String IP, String cacheResultCode,
                   String contentGatewayLengt, String clientRequestMethod, String proxyHierarchyRoute, String clientUserName,
                   String serverName, String proxyResponseContentType) {
        super();
        this.timeStampUTC = timeStampUTC;
        this.clientRequestDate = clientRequestDate;
        this.clientRequestTime = clientRequestTime;
        this.reguestProsessingTime = reguestProsessingTime;
        this.IP = IP;
        this.cacheResultCode = cacheResultCode;
        this.contentGatewayLengt = contentGatewayLengt;
        this.clientRequestMethod = clientRequestMethod;
        this.serverName = serverName;
        this.clientUserName = clientUserName;
        this.proxyHierarchyRoute = proxyHierarchyRoute;
        this.proxyResponseContentType = proxyResponseContentType;
    }

    public String getClientRequestDate() {
        return clientRequestDate;
    }

    public void setClientRequestDate(String clientRequestDate) {
        this.clientRequestDate = clientRequestDate;
    }

    public String getClientRequestTime() {
        return clientRequestTime;
    }

    public void setClientRequestTime(String clientRequestTime) {
        this.clientRequestTime = clientRequestTime;
    }

    public String getReguestProsessingTime() {
        return reguestProsessingTime;
    }

    public void setReguestProsessingTime(String reguestProsessingTime) {
        this.reguestProsessingTime = reguestProsessingTime;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String iP) {
        IP = iP;
    }

    public String getCacheResultCode() {
        return cacheResultCode;
    }

    public void setCacheResultCode(String cacheResultCode) {
        this.cacheResultCode = cacheResultCode;
    }

    public String getContentGatewayWhitBayt() {
        return contentGatewayLengt;
    }

    public void setContentGatewayWhitBayt(String contentGatewayLengt) {
        this.contentGatewayLengt = contentGatewayLengt;
    }

    public String getClientRequestMethod() {
        return clientRequestMethod;
    }

    public void setClientRequestMethod(String clientRequestMethod) {
        this.clientRequestMethod = clientRequestMethod;
    }

    public String getHostName() {
        return serverName;
    }

    public void setHostName(String serverName) {
        this.serverName = serverName;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    public String getProxyHierarchyRoute() {
        return proxyHierarchyRoute;
    }

    public void setProxyHierarchyRoute(String proxyHierarchyRoute) {
        this.proxyHierarchyRoute = proxyHierarchyRoute;
    }

    public String getProxyResponseContentType() {
        return proxyResponseContentType;
    }

    public void setProxyResponseContentType(String proxyResponseContentType) {
        this.proxyResponseContentType = proxyResponseContentType;
    }

}
