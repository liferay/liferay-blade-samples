# Customizing Warehouse Sourcing Logic for Order Shipments

This tutorial will show you how to add ... by implementing the `Foo` interface.

<Introduce the thing> Liferay Commerce provides ...

(Establishing Image Placeholder)

## Overview

1. [**Deploy an Example**](#deploy-an-example)
1. [**Walk Through the Example**](#walk-through-the-example)
1. [**Additional Information**](#additional-information)

## Deploy an Example

In this section, we will get an example ... up and running on your instance of Liferay Commerce. Follow these steps:

1. Start Liferay Commerce.

    ```bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.5
    ```

1. Download and unzip [Acme Commerce ...]().

    ```bash
    curl liferay-xxxx.zip
    ```

    ```bash
    unzip liferay-xxxx.zip
    ```

1. Go to `liferay-xxxx`.

    ```bash
    cd liferay-xxxx
    ```

1. Build and deploy the example.

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    >Note: This command is the same as copying the deployed jars to /opt/liferay/osgi/modules on the Docker container.

1. Confirm the deployment in the Liferay Docker container console.

    ```bash
    STARTED com.acme.xxxx.impl_1.0.0
    ```

1. Verify that the example ... was added. Open your browser to `https://localhost:8080` and navigate to ...

(Deployed Sample Image Placeholder)

Congratulations, you've successfully built and deployed a new ... that implements `Foo`.

Next, let's dive deeper to learn more.

## Walk Through the Example

In this section, we will review the example we deployed. First, we will annotate the class for OSGi registration. Second, we will review the `Foo` interface. And third, we will complete our implementation of `Foo`.

### Annotate Your Class for OSGi Registration

### Review the `Foo` Interface

Implement the following methods:

...

### Complete the ...

The ... is comprised of .... Do the following:

...

## Conclusion

Congratulations! You now know the basics for implementing the `Foo` interface, and have added a new ... to Liferay Commerce.

## Additional Information
