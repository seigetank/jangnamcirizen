FROM gitpod/workspace-full

USER root
RUN sudo apt-get update -y && \
    sudo apt-get install -y openjdk-17-jdk && \
    sudo apt-get clean
USER gitpod
