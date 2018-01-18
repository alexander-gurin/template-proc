package web;

public class Response {
    private Integer status;
    private String tmpl;

    public Response(Integer status, String tmpl) {
        this.status = status;
        this.tmpl = tmpl;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setTmpl(String tmpl) {
        this.tmpl = tmpl;
    }

    public String getTmpl() {
        return tmpl;
    }
}
