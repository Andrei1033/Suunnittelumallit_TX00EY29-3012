# ============================================================
#  Stage 1: build the fat JAR with Maven
# ============================================================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# pom.xml first so the dependency layer is cached
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src

# Bundle a plain .ttf Japanese font INTO the jar. JavaFX on Linux often cannot
# use CJK glyphs from system .ttc fonts (shows as empty boxes), so the app loads
# this file directly with Font.loadFont (see FontSupport.java).
RUN apt-get update \
    && apt-get install -y --no-install-recommends fonts-ipafont-gothic \
    && mkdir -p src/main/resources/fonts \
    && cp "$(find /usr/share/fonts -name 'ipag.ttf' | head -n 1)" src/main/resources/fonts/ipag.ttf \
    && rm -rf /var/lib/apt/lists/*

RUN mvn -B clean package -DskipTests

# ============================================================
#  Stage 2: runtime (GUI is shown on an external X server, e.g. Xming/VcXsrv)
# ============================================================
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends \
        libgl1 \
        libgtk-3-0 \
        libxtst6 \
        libxi6 \
        libxrender1 \
        libxext6 \
        fonts-dejavu-core \
    && fc-cache -f \
    && rm -rf /var/lib/apt/lists/*

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

COPY --from=build /app/target/temperature_converter.jar app.jar

CMD ["java", "-Dfile.encoding=UTF-8", "-Dsun.jnu.encoding=UTF-8", "-Dprism.order=sw", "-jar", "app.jar"]
