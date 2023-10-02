package com.mnzit;

import com.mnzit.dto.Packet;
import com.mnzit.enums.Part;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Manjit Shakya
 */
public class PacketEngine {

    private final Map<String, ReentrantLock> stringLocks = new ConcurrentHashMap<>();

    public void startTask(Packet packet) throws InterruptedException {
        Part part = packet.getPart();
        switch (part) {
            case HEAD ->{
                System.out.println("Running Head for: # " + packet.getId() + ", thread: " + Thread.currentThread().getName());
                Thread.sleep(1000);
            }

            case FIRST ->
                    System.out.println("Running First for: # " + packet.getId() + ", thread: " + Thread.currentThread().getName());
            case SECOND ->
                    System.out.println("Running Second for: # " + packet.getId() + ", thread: " + Thread.currentThread().getName());
            case THIRD ->
                    System.out.println("Running Third for: # " + packet.getId() + ", thread: " + Thread.currentThread().getName());
            case FOURTH ->
                    System.out.println("Running Fourth for: # " + packet.getId() + ", thread: " + Thread.currentThread().getName());
            case TAIL ->
                    System.out.println("Running Tail for: # " + packet.getId() + ", thread: " + Thread.currentThread().getName());
            case TAIL_DOWN ->
                    System.out.println("Running Tail Down for: # " + packet.getId() + ", thread: " + Thread.currentThread().getName());
        }
    }

    public void run(Packet packet) {
        ReentrantLock lock = stringLocks.computeIfAbsent(packet.getId(), key -> new ReentrantLock());
        lock.lock();
        try {
            PacketProcessor
                    .getInstance(packet.getId())
                    .process(packet, this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}





