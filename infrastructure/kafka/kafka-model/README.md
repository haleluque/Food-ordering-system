# Avro Schema to Java Class Generator

This project uses the [Apache Avro](https://avro.apache.org/) Maven plugin to automatically generate Java classes from Avro schema files during the Maven build process. Apache Avro is a data serialization system that provides rich data structures and a compact, fast binary format. This setup leverages the `avro-maven-plugin` to transform `.avsc` schema files into Java classes, enabling seamless integration of Avro data models into your Java application.

## Maven Configuration

Add the following to your `pom.xml`:

<dependencies>
    <dependency>
        <groupId>org.apache.avro</groupId>
        <artifactId>avro</artifactId>
        <version>${avro.version}</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.avro</groupId>
            <artifactId>avro-maven-plugin</artifactId>
            <version>${avro.version}</version>
            <configuration>
                <stringType>String</stringType>
                <enableDecimalLogicalType>true</enableDecimalLogicalType>
            </configuration>
            <executions>
                <execution>
                    <phase>generate-sources</phase>
                    <goals>
                        <goal>schema</goal>
                    </goals>
                    <configuration>
                        <sourceDirectory>src/main/resources/avro</sourceDirectory>
                        <outputDirectory>src/main/java</outputDirectory>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

## Directory Structure

- Place your `.avsc` schema files in `src/main/resources/avro`
- Generated Java classes will be placed in `src/main/java`

## Usage

Run the following command to generate Java classes from Avro schemas:
```bash
  mvn clean install
```
During the `generate-sources` phase, the plugin will process all `.avsc` files and generate corresponding Java classes.

## Notes

- `stringType` is set to `String` to use Java `String` instead of `Utf8`
- `enableDecimalLogicalType` is enabled to support Avro's decimal logical type

## References

- Apache Avro Documentation: https://avro.apache.org/docs/
- Avro Maven Plugin Guide: https://avro.apache.org/docs/current/getting-started-java/#maven
