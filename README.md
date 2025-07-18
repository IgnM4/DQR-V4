# DriveQuestRentals

This project uses Maven for building and testing.

## Setting up Maven

1. Install Maven locally:

   ```bash
   sudo apt-get update
   sudo apt-get install -y maven
   ```

2. Verify the installation:

   ```bash
   mvn -version
   ```

## Working without Internet access

If your environment restricts network access, Maven will not be able to download
dependencies from the public repositories. You can provide the needed artifacts
in advance or configure Maven to use an internal mirror.

### Using preloaded dependencies

1. On a machine with Internet access, run a full build so the dependencies are
   stored in your local Maven repository (`~/.m2/repository`).
2. Copy the entire `~/.m2/repository` directory to the target machine. Maven will
   use these artifacts when running with the `-o` (offline) flag:

   ```bash
   mvn -o package
   ```

### Configuring an internal repository

If you host an internal Maven repository (for example with [Nexus](https://www.sonatype.com/nexus-repository-oss) or Artifactory),
create or edit the `~/.m2/settings.xml` file and define a `<mirror>` section:

```xml
<settings>
  <mirrors>
    <mirror>
      <id>internal</id>
      <url>http://your.internal.repo/repository/maven-public</url>
      <mirrorOf>*</mirrorOf>
    </mirror>
  </mirrors>
</settings>
```

After configuring the mirror, Maven will fetch dependencies from the specified
internal repository instead of the public Internet.

