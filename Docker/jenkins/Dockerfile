FROM jenkins/jenkins:lts-jdk11

ENV JENKINS_USER=admin
ENV JENKINS_PASS=admin
ENV JAVA_OPTS=-Djenkins.install.runSetupWizard=false

ADD plugins.txt .
RUN jenkins-plugin-cli -f plugins.txt
ADD ssh-config .
USER root
ADD start.sh .
RUN mkdir /deploy-apigateway
RUN chown jenkins:jenkins /deploy-apigateway
RUN mkdir /deploy-cart
RUN chown jenkins:jenkins /deploy-cart
RUN mkdir /deploy-customers
RUN chown jenkins:jenkins /deploy-customers
RUN mkdir /deploy-eurekaserver
RUN chown jenkins:jenkins /deploy-eurekaserver
RUN mkdir /deploy-identity
RUN chown jenkins:jenkins /deploy-identity
RUN mkdir /deploy-payments
RUN chown jenkins:jenkins /deploy-payments
RUN mkdir /deploy-products
RUN chown jenkins:jenkins /deploy-products
RUN mkdir /deploy-ProductSearch
RUN chown jenkins:jenkins /deploy-ProductSearch
RUN mkdir /deploy-purchases
RUN chown jenkins:jenkins /deploy-purchases
RUN mkdir /deploy-suppliers
RUN chown jenkins:jenkins /deploy-suppliers

USER jenkins
ENTRYPOINT [ "/start.sh" ]