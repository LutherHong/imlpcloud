package zzh.inspur.testdocker;

import cn.hutool.core.convert.Convert;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.BuildImageResultCallback;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

/**
 * @author imlp
 * @date 2021/2/4 9:21
 */
public class DockerClientService {
    /**
     * 连接docker服务器
     *
     * @return
     */
    public DockerClient connectDocker() {
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://10.110.26.162:2375").build();
        Info info = dockerClient.infoCmd().exec();
        String infoStr = Convert.toStr(info);
        System.out.println("docker的环境信息如下：=================");
        System.out.println(info);
        return dockerClient;
    }

    /**
     * 拉取镜像
     */
    public void pullImage(DockerClient client, String imageName) {
        client.pullImageCmd(imageName).exec(new ResultCallback<PullResponseItem>() {
            @Override
            public void onStart(Closeable closeable) {
                System.out.println("++++++++++" + closeable);
            }

            @Override
            public void onNext(PullResponseItem pullResponseItem) {
                System.out.println(pullResponseItem.getStatus());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("pull finished");
            }

            @Override
            public void close() throws IOException {

            }
        });
    }

    /**
     * 创建容器
     *
     * @param client
     * @return
     */
    public CreateContainerResponse createContainers(DockerClient client, String containerName, String imageName) {
        //映射端口8088—>80
        ExposedPort tcp80 = ExposedPort.tcp(80);
        Ports portBindings = new Ports();
        portBindings.bind(tcp80, Ports.Binding.bindPort(8089));

        CreateContainerResponse container = client.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(newHostConfig().withPortBindings(portBindings))
                .withExposedPorts(tcp80)
                .exec();
        return container;
    }


    /**
     * 启动容器
     *
     * @param client
     * @param containerId
     */
    public void startContainer(DockerClient client, String containerId) {
        client.startContainerCmd(containerId).exec();
    }

    /**
     * 停止容器
     *
     * @param client
     * @param containerId
     */
    public void stopContainer(DockerClient client, String containerId) {
        client.stopContainerCmd(containerId).exec();
    }

    /**
     * 删除容器
     *
     * @param client
     * @param containerId
     */
    public void removeContainer(DockerClient client, String containerId) {
        client.removeContainerCmd(containerId).exec();
    }

    /**
     * 搜索镜像
     */
    public void searchImage(DockerClient client, String imageName) {
        List<SearchItem> dockerSearch = client.searchImagesCmd(imageName).exec();
        System.out.println("Search returned" + dockerSearch.toString());
    }

    /**
     * 创建镜像
     */
    public String buildImage(DockerClient client, String imageName, String tag, String dockerfileName) {
        String path = this.getClass().getClassLoader().getResource("").getPath();//注意getResource("")里面是空字符串
        System.out.println(path);
        File file = new File(path + dockerfileName);
        BuildImageResultCallback callback = new BuildImageResultCallback() {
            @Override
            public void onNext(BuildResponseItem item) {
                System.out.println("" + item);
                super.onNext(item);
            }
        };
        String imageId = client.buildImageCmd(file).withTag(imageName + ":" + tag).exec(callback).awaitImageId();
        //List<Image> images = client.listImagesCmd().withShowAll(true).exec();
        return imageId;
    }
}
