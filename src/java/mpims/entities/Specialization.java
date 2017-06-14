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
@Table(name = "specialization", catalog = "mpims", schema = "")
@NamedQueries({@NamedQuery(name = "Specialization.findAll", query = "SELECT s FROM Specialization s"), @NamedQuery(name = "Specialization.findById", query = "SELECT s FROM Specialization s WHERE s.id = :id"), @NamedQuery(name = "Specialization.findBySpecializationName", query = "SELECT s FROM Specialization s WHERE s.specializationName = :specializationName")})
public class Specialization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "specialization_name", nullable = false, length = 200)
    private String specializationName;

    public Specialization() {
    }

    public Specialization(Integer id) {
        this.id = id;
    }

    public Specialization(Integer id, String specializationName) {
        this.id = id;
        this.specializationName = specializationName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
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
        if (!(object instanceof Specialization)) {
            return false;
        }
        Specialization other = (Specialization) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mpims.entities.Specialization[id=" + id + "]";
    }

}
