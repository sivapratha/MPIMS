/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mpims.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sivapratha Marimuthu
 */
@Entity
@Table(name = "doctor_info", catalog = "mpims", schema = "")
@NamedQueries({@NamedQuery(name = "DoctorInfo.findAll", query = "SELECT d FROM DoctorInfo d"), @NamedQuery(name = "DoctorInfo.findById", query = "SELECT d FROM DoctorInfo d WHERE d.id = :id"), @NamedQuery(name = "DoctorInfo.findByFname", query = "SELECT d FROM DoctorInfo d WHERE d.fname = :fname"), @NamedQuery(name = "DoctorInfo.findByLname", query = "SELECT d FROM DoctorInfo d WHERE d.lname = :lname"), @NamedQuery(name = "DoctorInfo.findByAddress", query = "SELECT d FROM DoctorInfo d WHERE d.address = :address"), @NamedQuery(name = "DoctorInfo.findByLocality", query = "SELECT d FROM DoctorInfo d WHERE d.locality = :locality"), @NamedQuery(name = "DoctorInfo.findByCity", query = "SELECT d FROM DoctorInfo d WHERE d.city = :city"), @NamedQuery(name = "DoctorInfo.findByPhone", query = "SELECT d FROM DoctorInfo d WHERE d.phone = :phone"), @NamedQuery(name = "DoctorInfo.findByEmail", query = "SELECT d FROM DoctorInfo d WHERE d.email = :email"), @NamedQuery(name = "DoctorInfo.findByWeb", query = "SELECT d FROM DoctorInfo d WHERE d.web = :web"), @NamedQuery(name = "DoctorInfo.findByCno", query = "SELECT d FROM DoctorInfo d WHERE d.cno = :cno"), @NamedQuery(name = "DoctorInfo.findByEdu", query = "SELECT d FROM DoctorInfo d WHERE d.edu = :edu"), @NamedQuery(name = "DoctorInfo.findBySpec", query = "SELECT d FROM DoctorInfo d WHERE d.spec = :spec"), @NamedQuery(name = "DoctorInfo.findByHospital", query = "SELECT d FROM DoctorInfo d WHERE d.hospital = :hospital"), @NamedQuery(name = "DoctorInfo.findByTime", query = "SELECT d FROM DoctorInfo d WHERE d.time = :time")})
public class DoctorInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fname", nullable = false, length = 20)
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname", nullable = false, length = 20)
    private String lname;
    @Basic(optional = false)
    @Column(name = "address", nullable = false, length = 40)
    private String address;
    @Basic(optional = false)
     @Column(name = "locality", nullable = false, length = 20)
    private String locality;    
    @Column(name = "city", nullable = false, length = 20)
    private String city;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 20)
    private String email;
    @Basic(optional = false)
    @Column(name = "web", nullable = false, length = 20)
    private String web;
    @Basic(optional = false)
    @Column(name = "cno", nullable = false, length = 10)
    private String cno;
    @Basic(optional = false)
    @Column(name = "edu", nullable = false, length = 20)
    private String edu;
    @Basic(optional = false)
    @Column(name = "spec", nullable = false, length = 20)
    private String spec;
    @Basic(optional = false)
    @Column(name = "hospital", nullable = false, length = 30)
    private String hospital;
    @Basic(optional = false)
    @Column(name = "time", nullable = false, length = 10)
    private String time;

    public DoctorInfo() {
    }

    public DoctorInfo(Integer id) {
        this.id = id;
    }

    public DoctorInfo(Integer id, String fname, String lname, String address, String city,String locality, String phone, String email, String web, String cno, String edu, String spec, String hospital, String time) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.locality = locality;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.web = web;
        this.cno = cno;
        this.edu = edu;
        this.spec = spec;
        this.hospital = hospital;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocality() {
        return locality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorInfo)) {
            return false;
        }
        DoctorInfo other = (DoctorInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mpims.entities.DoctorInfo[id=" + id + "]";
    }

}
