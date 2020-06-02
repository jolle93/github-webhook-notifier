# github-webhook-notifier 

Just a short Quarkus service to send mails on github web hook actions.

This repository is just a short program of an assessment program.


This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

# development status
**under development**

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `github-webhook-notifier-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/github-webhook-notifier-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/github-webhook-notifier-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Application Configuration
```
github.user.access.token= <access token>
github-api/mp-rest/url=https://api.github.com
github-api/mp-rest/scope=javax.inject.Singleton
quarkus.mailer.from=<email address>
quarkus.mailer.host=<host address>
quarkus.mailer.port=<host port>
quarkus.mailer.trust-all=true  #could be different 
quarkus.mailer.start-tls=REQUIRED #could be different, if you use ssl
quarkus.mailer.username=<credentials>
quarkus.mailer.password=<credentials>
quarkus.mailer.mock=false #true for development
cron.expression=0 15 6 ? * * <actual every day at 6.15am>
mail.fallback.address=<if the owner of this application has no public mail at github>
```
For more information on mailer config [see](https://quarkus.io/guides/mailer)

