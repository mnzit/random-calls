package com.mnzit.dto;

import com.mnzit.enums.Part;

/**
 * @author Manjit Shakya
 */
public class Packet {

    private String id;
    private Part part;

    public Packet(String id) {
        this.id = id;
    }

    public Packet(String id, Part part) {
        this.id = id;
        this.part = part;
    }

    public Packet(Part part) {
        this.part = part;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return this.part.toString();
    }
}
