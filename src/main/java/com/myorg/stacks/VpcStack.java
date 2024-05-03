package com.myorg.stacks;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;

public class VpcStack extends Stack {

    private Vpc vpc;
    public VpcStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public VpcStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        vpc = software.amazon.awscdk.services.ec2.Vpc.Builder.create(this, "Vpc-1")
                .maxAzs(2)
                .natGateways(0)
                .build();
    }

    public Vpc getVpc() {
        return vpc;
    }
}
