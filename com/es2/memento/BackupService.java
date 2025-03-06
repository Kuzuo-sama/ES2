package com.es2.memento;


import java.util.concurrent.ConcurrentHashMap;

public class BackupService extends Object{
    private Server server;
    private ConcurrentHashMap<Integer, Memento> snapshots = new ConcurrentHashMap<>();
    private int counter = 0;

    public BackupService(Server server) {
        this.server = server;
    }

    public void takeSnapshot() {
        snapshots.put(counter, new Memento(server.getStudentNames()));
        counter++;
    }

    public void restoreSnapshot(int snapshotNumber) throws NotExistingSnapshotException {
        if (snapshots.containsKey(snapshotNumber)) {
            server.restore(snapshots.get(snapshotNumber));
        }else{
            throw new NotExistingSnapshotException();
        }
    
    }

}
