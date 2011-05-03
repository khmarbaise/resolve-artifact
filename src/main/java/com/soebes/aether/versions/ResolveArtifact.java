package com.soebes.aether.versions;

import org.apache.log4j.Logger;
import org.sonatype.aether.RepositorySystem;
import org.sonatype.aether.RepositorySystemSession;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.resolution.ArtifactRequest;
import org.sonatype.aether.resolution.ArtifactResolutionException;
import org.sonatype.aether.resolution.ArtifactResult;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import com.soebes.aether.versions.internal.Booter;
import com.soebes.aether.versions.internal.Repositories;

/**
 * ResolveArtifact is intended to resolve an artifact which
 * means to download the artifact if it is not downloaded yet.
 *
 * @author Karl Heinz Marbaise
 *
 */
public class ResolveArtifact {
    private static Logger LOGGER = Logger.getLogger(ResolveArtifact.class);

    private RepositorySystemSession repositorySystemSession;
    private RepositorySystem repositorySystem;
    private ArtifactRequest artifactRequest;

    public ResolveArtifact() {
        setRepositorySystem(Booter.newRepositorySystem());

        setRepositorySystemSession(Booter.newRepositorySystemSession( getRepositorySystem() ));
        setArtifactRequest(new ArtifactRequest());
//FIXME: The following repository must be solved by reading the settings.xml file instead of hard coding the repositories!!!
//Add all repositories we have to the list of repositories.
        for(RemoteRepository repository : Repositories.STANDARD) {
            getArtifactRequest().addRepository( repository );
        }
    }

    public ArtifactResult resolveArtifact(String groupId, String artifactId, String version) {
        Artifact artifact = new DefaultArtifact( groupId, artifactId, null, version);

        getArtifactRequest().setArtifact(artifact);

        ArtifactResult artifactResult = null;
        try {
            artifactResult = getRepositorySystem().resolveArtifact( getRepositorySystemSession(), getArtifactRequest() );
        } catch (ArtifactResolutionException e) {
            LOGGER.fatal("ArtifactResolutionException:" , e);
        }

        return artifactResult;
    }

    public ArtifactResult resolveArtifactWithCoordinates(String groupId, String artifactId, String version) {
        Artifact artifact = new DefaultArtifact( groupId + ":" + artifactId + ":" + version);
        getArtifactRequest().setArtifact(artifact);

        ArtifactResult artifactResult = null;
        try {
            artifactResult = getRepositorySystem().resolveArtifact( getRepositorySystemSession(), getArtifactRequest() );
        } catch (ArtifactResolutionException e) {
            LOGGER.fatal("ArtifactResolutionException:" , e);
        }

        return artifactResult;
    }

    public void setArtifactRequest(ArtifactRequest artifactRequest) {
        this.artifactRequest = artifactRequest;
    }

    public ArtifactRequest getArtifactRequest() {
        return artifactRequest;
    }

    public void setRepositorySystem(RepositorySystem repositorySystem) {
        this.repositorySystem = repositorySystem;
    }

    public RepositorySystem getRepositorySystem() {
        return repositorySystem;
    }

    public void setRepositorySystemSession(RepositorySystemSession repositorySystemSession) {
        this.repositorySystemSession = repositorySystemSession;
    }

    public RepositorySystemSession getRepositorySystemSession() {
        return repositorySystemSession;
    }


}
