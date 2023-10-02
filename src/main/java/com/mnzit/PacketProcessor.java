package com.mnzit;

import com.mnzit.dto.Packet;
import com.mnzit.dto.PacketExecutable;
import com.mnzit.enums.Part;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Manjit Shakya
 */
public class PacketProcessor {

    private static final Map<String, PacketProcessor> instances = new ConcurrentHashMap<>();

    private final Map<Part, PacketExecutable> executableMap = new ConcurrentHashMap<>();

    private PacketProcessor() {
        initMap();
    }

    private void initMap() {
        Part[] parts = Part.values();
        //Iterating enum using the for loop
        PacketExecutable prev = null;
        for (Part part : parts) {
            if (part != Part.TAIL_DOWN) {
                if (part == Part.HEAD) {
                    prev = new PacketExecutable(new Packet(Part.HEAD));
                    executableMap.put(Part.HEAD, prev);
                } else {
                    PacketExecutable executable = new PacketExecutable(new Packet(part));
                    prev.setNext(executable);
                    executable.setPrev(prev);
                    prev = executable;
                    executableMap.put(part, executable);
                }
            }
        }
    }

    public static PacketProcessor getInstance(String id) {
        if (!instances.containsKey(id)) {
            PacketProcessor instance = new PacketProcessor();
            instances.put(id, instance);
        }
        return instances.get(id);
    }

    public void process(Packet packet, PacketEngine packetEngine) throws InterruptedException {
        synchronized (executableMap) {
            Part[] parts = Part.values();
            for (Part part : parts) {
                if (part != Part.TAIL_DOWN) {
                    if (part == Part.HEAD && !executableMap.get(part).isExecuted()) {
                        PacketExecutable executable = executableMap.get(part);
                        executable.getPacket().setId(packet.getId());
                        packetEngine.startTask(executable.getPacket());
                        executable.setExecuted(true);
                    } else {
                        if (executableMap.get(part).getPrev().get().isExecuted() && !executableMap.get(part).isExecuted()) {
                            PacketExecutable executable = executableMap.get(part);
                            executable.getPacket().setId(packet.getId());
                            packetEngine.startTask(executable.getPacket());
                            executable.setExecuted(true);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
}
