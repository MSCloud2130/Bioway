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
RUN chmod -R 777 /deploy-apigateway
RUN mkdir /deploy-cart
RUN chmod -R 777 /deploy-cart
RUN mkdir /deploy-customers
RUN chmod -R 777 /deploy-customers
RUN mkdir /deploy-eurekaserver
RUN chmod -R 777 /deploy-eurekaserver
RUN mkdir /deploy-identity
RUN chmod -R 777 /deploy-identity
RUN mkdir /deploy-payments
RUN chmod -R 777 /deploy-payments
RUN mkdir /deploy-products
RUN chmod -R 777 /deploy-products
RUN mkdir /deploy-ProductSearch
RUN chmod -R 777 /deploy-ProductSearch
RUN mkdir /deploy-purchases
RUN chmod -R 777 /deploy-purchases
RUN mkdir /deploy-suppliers
RUN chmod -R 777 /deploy-suppliers

USER jenkins
ENTRYPOINT [ "/start.sh" ]