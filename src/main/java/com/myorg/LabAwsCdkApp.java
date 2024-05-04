package com.myorg;

import com.myorg.stacks.ClusterStack;
import com.myorg.stacks.RdsStack;
import com.myorg.stacks.Service01Stack;
import com.myorg.stacks.VpcStack;
import software.amazon.awscdk.App;

public class LabAwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        VpcStack vpc = new VpcStack(app, "Vpc");

        ClusterStack cluster = new ClusterStack(app, "Cluster", vpc.getVpc());
        cluster.addDependency(vpc);

        RdsStack rds = new RdsStack(app, "Rds", vpc.getVpc());
        rds.addDependency(vpc);

        Service01Stack service01 = new Service01Stack(app, "Service01", cluster.getCluster());
        service01.addDependency(cluster);
        service01.addDependency(rds);

        app.synth();
    }
}

