package com.soebes.aether.versions.internal;

import java.util.Arrays;
import java.util.List;

import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.repository.RepositoryPolicy;

public class Repositories {
    public static RemoteRepository create(String id, String url, String snapshotUpdates, String releaseUpdates) {
        RemoteRepository repository;

        repository = new RemoteRepository(id, "default", url);
        repository.setPolicy(true, new RepositoryPolicy(snapshotUpdates != null, snapshotUpdates, RepositoryPolicy.CHECKSUM_POLICY_WARN));
        repository.setPolicy(false, new RepositoryPolicy(releaseUpdates != null, releaseUpdates, RepositoryPolicy.CHECKSUM_POLICY_WARN));
        return repository;
    }

    public static final RemoteRepository MAVEN_CENTRAL = create(
        "central",
        "http://repo1.maven.org/maven2/",
        null,
        RepositoryPolicy.UPDATE_POLICY_DAILY
    );

    public static final List<RemoteRepository> STANDARD = Arrays.asList(MAVEN_CENTRAL);

}
