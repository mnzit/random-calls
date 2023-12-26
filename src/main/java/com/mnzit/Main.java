package com.mnzit;

import com.mnzit.dto.Packet;
import com.mnzit.enums.Part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Manjit Shakya
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        PacketEngine packetEngine = new PacketEngine();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        success(executorService, packetEngine);

    }

    public static void success(ExecutorService executorService, PacketEngine packetEngine) throws InterruptedException {
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String uuid = UUID.randomUUID().toString();
            tasks.addAll(List.of(
                    () -> {
                        packetEngine.run(new Packet(uuid, Part.HEAD));
                        return true;
                    },
                    () -> {
                        packetEngine.run(new Packet(uuid, Part.FIRST));
                        return true;
                    },
                    () -> {
                        packetEngine.run(new Packet(uuid, Part.SECOND));
                        return true;
                    },
                    () -> {
                        packetEngine.run(new Packet(uuid, Part.THIRD));
                        return true;
                    },
                    () -> {
                        packetEngine.run(new Packet(uuid, Part.FOURTH));
                        return true;
                    },
                    () -> {
                        packetEngine.run(new Packet(uuid, Part.TAIL));
                        return true;
                    }
            ));
        }
        Collections.shuffle(tasks);
        executorService.invokeAll(tasks);
    }

    public static void failure(ExecutorService executorService, PacketEngine packetEngine, String randomUUID) throws InterruptedException {
        List<Callable<Boolean>> tasks = List.of(
                () -> {
                    packetEngine.run(new Packet(randomUUID, Part.HEAD));
                    return true;
                },
                () -> {
                    packetEngine.run(new Packet(randomUUID, Part.FIRST));
                    return true;
                },
                () -> {
                    packetEngine.run(new Packet(randomUUID, Part.TAIL_DOWN));
                    return true;
                }
        );

        executorService.invokeAll(tasks);
    }
}
