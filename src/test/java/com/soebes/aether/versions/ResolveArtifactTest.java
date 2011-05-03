package com.soebes.aether.versions;

import org.sonatype.aether.resolution.ArtifactResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.soebes.aether.versions.ResolveArtifact;

/**
 *
 * @author Karl Heinz Marbaise
 *
 */
public class ResolveArtifactTest {
    private ResolveArtifact resolveArtifact;

    @BeforeClass
    public void beforeClass() {
        resolveArtifact = new ResolveArtifact();
    }

    @Test
    public void checkIfWeGetArtifactWithGroupIdArtifactIdExtensionVersion() {
        ArtifactResult artifact = resolveArtifact.resolveArtifact("com.soebes.subversion.sapm", "sapm", "0.4");
        System.out.println("Repository:" + artifact.getRepository());
        System.out.println("Request:" + artifact.getRequest());
        System.out.println("Artifact:" + artifact.getArtifact());
    }

    @Test
    public void checkIfWeGetArtifactWithCoordinates() {
        ArtifactResult artifact = resolveArtifact.resolveArtifactWithCoordinates("com.soebes.subversion.sapm", "sapm", "0.4");
        System.out.println("Repository:" + artifact.getRepository());
        System.out.println("Request:" + artifact.getRequest());
        System.out.println("Artifact:" + artifact.getArtifact());
    }
}
