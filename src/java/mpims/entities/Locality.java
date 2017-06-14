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
@Table(name = "locality", catalog = "mpims", schema = "")
@NamedQueries({@NamedQuery(name = "Locality.findAll", query = "SELECT c FROM Locality c"), @NamedQuery(name = "Locality.findById", query = "SELECT c FROM Locality c WHERE c.id = :id"), @NamedQuery(name = "Locality.findByLocalityName", query = "SELECT c FROM Locality c WHERE c.localityName = :localityName")})
public class Locality implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "locality_name", nullable = false, length = 200)
    private String localityName;

    

    public Locality() {
    }

    public Locality(Integer id) {
        this.id = id;
    }

    public Locality(Integer id, String localityName) {
        this.id = id;
        this.localityName = localityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getLocalityName() {
        return localityName;
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
        if (!(object instanceof Locality)) {
            return false;
        }
        Locality other = (Locality) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mpims.entities.Locality[id=" + id + "]";
    }

}
