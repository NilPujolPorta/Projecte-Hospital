/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.model.business.entities;

import APP_Hospital.model.business.utils.NumberUtils;

/**
 *
 * @author nilp2
 */
public abstract class Entity {
    private long id;
    public Entity(){
        id = NumberUtils.unsaved;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        if (getId()!=NumberUtils.unsaved) {
            throw new UnsupportedOperationException("id cannot be changed");
        }
        if (id<=NumberUtils.idPersona) {
            throw new IllegalArgumentException("id cannot be negative or zero");
        }
        
        this.id = id;
    }
    
}
