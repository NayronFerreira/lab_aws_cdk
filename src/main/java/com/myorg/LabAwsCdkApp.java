package com.myorg;

import com.myorg.stacks.*;
import software.amazon.awscdk.App;

public class LabAwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        VpcStack vpc = new VpcStack(app, "Vpc");

        ClusterStack cluster = new ClusterStack(app, "Cluster", vpc.getVpc());
        cluster.addDependency(vpc);

        RdsStack rds = new RdsStack(app, "Rds", vpc.getVpc());
        rds.addDependency(vpc);

        SnsStack sns = new SnsStack(app, "Sns");

        Service01Stack service01 = new Service01Stack(app, "Service01", cluster.getCluster(), sns.getProductEventsTopic());
        service01.addDependency(cluster);
        service01.addDependency(rds);
        service01.addDependency(sns);

        Service02Stack service02 = new Service02Stack(app, "Service02", cluster.getCluster(), sns.getProductEventsTopic());
        service02.addDependency(cluster);
        service02.addDependency(sns);

        app.synth();
    }
}

