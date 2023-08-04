public class registrosC {
    private String codigo;
    private String cedula;
    private String nombre;

    private String fecha;
    private String signo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public registrosC(String codigo, String cedula, String nombre, String fecha, String signo) {
        this.codigo = codigo;
        this.cedula = cedula;
        this.nombre = nombre;
        this.fecha = fecha;
        this.signo = signo;
    }

    public registrosC() {
    }
}
