package org.kie.tests.wb.live;

import static org.kie.tests.wb.base.util.TestConstants.KJAR_DEPLOYMENT_ID;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.kie.remote.tests.base.unit.GetIgnoreRule;
import org.kie.remote.tests.base.unit.GetIgnoreRule.IgnoreIfGETFails;
import org.kie.tests.wb.base.methods.RepositoryDeploymentUtil;

public class RepositoryDeploymentUtilLiveTest {

    private static String user = "mary";
    private static String password = "mary123@";

    @Rule
    public GetIgnoreRule getIgnoreRule = new GetIgnoreRule();

    private static URL deploymentUrl;
    static {
        try {
            deploymentUrl =  new URL("http://localhost:8080/kie-wb/");
        } catch( MalformedURLException e ) {
            // do nothing
        }
    }

    @Test
    @IgnoreIfGETFails(getUrl="http://localhost:8080/kie-wb/rest/deployment")
    public void optimizedRepeatedCalls() {
        // create repo if not present
        RepositoryDeploymentUtil deployUtil = new RepositoryDeploymentUtil(deploymentUrl, user, password, 5);
        String repoUrl = "https://github.com/droolsjbpm/jbpm-playground.git";
        String repositoryName = "tests";
        String project = "integration-tests";
        String orgUnit = UUID.randomUUID().toString();
        orgUnit = orgUnit.substring(0, orgUnit.indexOf("-"));

        deployUtil.createRepositoryAndDeployProject(repoUrl, repositoryName, project, KJAR_DEPLOYMENT_ID, orgUnit);
    }
}
