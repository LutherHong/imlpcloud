package zzh.inspur.testdocker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author imlp
 * @date 2021/2/4 15:26
 */
public class TestDocker {
    public static void main(String[] args) throws FileNotFoundException {
        DockerClientService dockerClientService = new DockerClientService();
        DockerClient client = dockerClientService.connectDocker();
//        String containerName = "nginxremote";
//        String imageName = "nginx";
////        dockerClientService.pullImage(client, imageName);
//        dockerClientService.searchImage(client,imageName);
//        CreateContainerResponse container = dockerClientService.createContainers(client, containerName, imageName);
//        dockerClientService.startContainer(client, container.getId());

        String imageName = "testdocekrfile";
        String tag = "0.1";
        String dockerfileName = "dockerfile";
        String imagesId = dockerClientService.buildImage(client, imageName, tag, dockerfileName);
        System.out.println(imageName);

    }
}
