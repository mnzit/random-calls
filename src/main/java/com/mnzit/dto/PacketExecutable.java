package com.mnzit.dto;

import java.util.Optional;

public class PacketExecutable {

    private Packet packet;
    private Optional<PacketExecutable> next = Optional.empty();
    private Optional<PacketExecutable> prev = Optional.empty();
    private boolean isExecuted;

    public PacketExecutable(Packet packet) {
        this.packet = packet;
    }

    public PacketExecutable() {
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public Optional<PacketExecutable> getNext() {
        return next;
    }

    public void setNext(PacketExecutable next) {
        this.next = Optional.of(next);
    }

    public Optional<PacketExecutable> getPrev() {
        return prev;
    }

    public void setPrev(PacketExecutable prev) {
        this.prev = Optional.of(prev);
    }

    public boolean isExecuted() {
        return this.packet != null && isExecuted;
    }

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }

    @Override
    public String toString() {
        return "exec: " + this.isExecuted + ", curr: " + this.getPacket() + ", prev: " + (this.prev.isPresent() ? this.prev.get().getPacket().getPart() : "null") + ", next: " + (this.next.isPresent() ? this.next.get().getPacket().getPart() : "null");
    }


}