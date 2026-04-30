# 项目代码上下文

> 扫描范围：.

## 项目目录结构 (目标范围: .\.)
```text
./
    .gitattributes
    .gitignore
    cmd.txt
    HELP.md
    hs_err_pid25852.log
    mvnw
    mvnw.cmd
    pom.xml
    .mvn/
        wrapper/
            maven-wrapper.properties
    src/
        main/
            java/
                com/
                    express/
                        servermanagertool/
                            Launcher.java
                            core/
                                TerminalSession.java
                            model/
                                Credential.java
                                FileInfo.java
                            ui/
                                MainApp.java
                                RemoteFileBrowser.java
                                SshTerminal.java
                            util/
                                ConfigManager.java
            resources/
                application.properties
                static/
                templates/
                    index.html
        test/
            java/
                com/
                    express/
                        servermanagertool/
                            ServerManagerToolApplicationTests.java
```

## 每一个文件的代码内容

### 文件路径: `.gitattributes`
```text
/mvnw text eol=lf
*.cmd text eol=crlf

```

### 文件路径: `.gitignore`
```text
HELP.md
target/
.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/

### STS ###
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

### IntelliJ IDEA ###
.idea
*.iws
*.iml
*.ipr

### NetBeans ###
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
build/
!**/src/main/**/build/
!**/src/test/**/build/

### VS Code ###
.vscode/

```

### 文件路径: `cmd.txt`
```text
终端执行脚本获取项目目录以及内容
python generate_ai_context.py
```

### 文件路径: `HELP.md`
```markdown
# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.6/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


```

### 文件路径: `hs_err_pid25852.log`
```text
// 读取失败: 'utf-8' codec can't decode byte 0xba in position 502709: invalid start byte
```

### 文件路径: `mvnw`
```text
#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.3.4
#
# Optional ENV vars
# -----------------
#   JAVA_HOME - location of a JDK home dir, required when download maven via java source
#   MVNW_REPOURL - repo url base for downloading maven distribution
#   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
#   MVNW_VERBOSE - true: enable verbose log; debug: trace the mvnw script; others: silence the output
# ----------------------------------------------------------------------------

set -euf
[ "${MVNW_VERBOSE-}" != debug ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# set JAVACMD and JAVACCMD
set_java_home() {
  # For Cygwin and MinGW, ensure paths are in Unix format before anything is touched
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/bin/java"
      JAVACCMD="$JAVA_HOME/bin/javac"

      if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
        echo "The JAVA_HOME environment variable is not defined correctly, so mvnw cannot run." >&2
        echo "JAVA_HOME is set to \"$JAVA_HOME\", but \"\$JAVA_HOME/bin/java\" or \"\$JAVA_HOME/bin/javac\" does not exist." >&2
        return 1
      fi
    fi
  else
    JAVACMD="$(
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v java
    )" || :
    JAVACCMD="$(
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v javac
    )" || :

    if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
      echo "The java/javac command does not exist in PATH nor is JAVA_HOME set, so mvnw cannot run." >&2
      return 1
    fi
  fi
}

# hash string like Java String::hashCode
hash_string() {
  str="${1:-}" h=0
  while [ -n "$str" ]; do
    char="${str%"${str#?}"}"
    h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
    str="${str#?}"
  done
  printf %x\\n $h
}

verbose() { :; }
[ "${MVNW_VERBOSE-}" != true ] || verbose() { printf %s\\n "${1-}"; }

die() {
  printf %s\\n "$1" >&2
  exit 1
}

trim() {
  # MWRAPPER-139:
  #   Trims trailing and leading whitespace, carriage returns, tabs, and linefeeds.
  #   Needed for removing poorly interpreted newline sequences when running in more
  #   exotic environments such as mingw bash on Windows.
  printf "%s" "${1}" | tr -d '[:space:]'
}

scriptDir="$(dirname "$0")"
scriptName="$(basename "$0")"

# parse distributionUrl and optional distributionSha256Sum, requires .mvn/wrapper/maven-wrapper.properties
while IFS="=" read -r key value; do
  case "${key-}" in
  distributionUrl) distributionUrl=$(trim "${value-}") ;;
  distributionSha256Sum) distributionSha256Sum=$(trim "${value-}") ;;
  esac
done <"$scriptDir/.mvn/wrapper/maven-wrapper.properties"
[ -n "${distributionUrl-}" ] || die "cannot read distributionUrl property in $scriptDir/.mvn/wrapper/maven-wrapper.properties"

case "${distributionUrl##*/}" in
maven-mvnd-*bin.*)
  MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/
  case "${PROCESSOR_ARCHITECTURE-}${PROCESSOR_ARCHITEW6432-}:$(uname -a)" in
  *AMD64:CYGWIN* | *AMD64:MINGW*) distributionPlatform=windows-amd64 ;;
  :Darwin*x86_64) distributionPlatform=darwin-amd64 ;;
  :Darwin*arm64) distributionPlatform=darwin-aarch64 ;;
  :Linux*x86_64*) distributionPlatform=linux-amd64 ;;
  *)
    echo "Cannot detect native platform for mvnd on $(uname)-$(uname -m), use pure java version" >&2
    distributionPlatform=linux-amd64
    ;;
  esac
  distributionUrl="${distributionUrl%-bin.*}-$distributionPlatform.zip"
  ;;
maven-mvnd-*) MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/ ;;
*) MVN_CMD="mvn${scriptName#mvnw}" _MVNW_REPO_PATTERN=/org/apache/maven/ ;;
esac

# apply MVNW_REPOURL and calculate MAVEN_HOME
# maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
[ -z "${MVNW_REPOURL-}" ] || distributionUrl="$MVNW_REPOURL$_MVNW_REPO_PATTERN${distributionUrl#*"$_MVNW_REPO_PATTERN"}"
distributionUrlName="${distributionUrl##*/}"
distributionUrlNameMain="${distributionUrlName%.*}"
distributionUrlNameMain="${distributionUrlNameMain%-bin}"
MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"
MAVEN_HOME="${MAVEN_USER_HOME}/wrapper/dists/${distributionUrlNameMain-}/$(hash_string "$distributionUrl")"

exec_maven() {
  unset MVNW_VERBOSE MVNW_USERNAME MVNW_PASSWORD MVNW_REPOURL || :
  exec "$MAVEN_HOME/bin/$MVN_CMD" "$@" || die "cannot exec $MAVEN_HOME/bin/$MVN_CMD"
}

if [ -d "$MAVEN_HOME" ]; then
  verbose "found existing MAVEN_HOME at $MAVEN_HOME"
  exec_maven "$@"
fi

case "${distributionUrl-}" in
*?-bin.zip | *?maven-mvnd-?*-?*.zip) ;;
*) die "distributionUrl is not valid, must match *-bin.zip or maven-mvnd-*.zip, but found '${distributionUrl-}'" ;;
esac

# prepare tmp dir
if TMP_DOWNLOAD_DIR="$(mktemp -d)" && [ -d "$TMP_DOWNLOAD_DIR" ]; then
  clean() { rm -rf -- "$TMP_DOWNLOAD_DIR"; }
  trap clean HUP INT TERM EXIT
else
  die "cannot create temp dir"
fi

mkdir -p -- "${MAVEN_HOME%/*}"

# Download and Install Apache Maven
verbose "Couldn't find MAVEN_HOME, downloading and installing it ..."
verbose "Downloading from: $distributionUrl"
verbose "Downloading to: $TMP_DOWNLOAD_DIR/$distributionUrlName"

# select .zip or .tar.gz
if ! command -v unzip >/dev/null; then
  distributionUrl="${distributionUrl%.zip}.tar.gz"
  distributionUrlName="${distributionUrl##*/}"
fi

# verbose opt
__MVNW_QUIET_WGET=--quiet __MVNW_QUIET_CURL=--silent __MVNW_QUIET_UNZIP=-q __MVNW_QUIET_TAR=''
[ "${MVNW_VERBOSE-}" != true ] || __MVNW_QUIET_WGET='' __MVNW_QUIET_CURL='' __MVNW_QUIET_UNZIP='' __MVNW_QUIET_TAR=v

# normalize http auth
case "${MVNW_PASSWORD:+has-password}" in
'') MVNW_USERNAME='' MVNW_PASSWORD='' ;;
has-password) [ -n "${MVNW_USERNAME-}" ] || MVNW_USERNAME='' MVNW_PASSWORD='' ;;
esac

if [ -z "${MVNW_USERNAME-}" ] && command -v wget >/dev/null; then
  verbose "Found wget ... using wget"
  wget ${__MVNW_QUIET_WGET:+"$__MVNW_QUIET_WGET"} "$distributionUrl" -O "$TMP_DOWNLOAD_DIR/$distributionUrlName" || die "wget: Failed to fetch $distributionUrl"
elif [ -z "${MVNW_USERNAME-}" ] && command -v curl >/dev/null; then
  verbose "Found curl ... using curl"
  curl ${__MVNW_QUIET_CURL:+"$__MVNW_QUIET_CURL"} -f -L -o "$TMP_DOWNLOAD_DIR/$distributionUrlName" "$distributionUrl" || die "curl: Failed to fetch $distributionUrl"
elif set_java_home; then
  verbose "Falling back to use Java to download"
  javaSource="$TMP_DOWNLOAD_DIR/Downloader.java"
  targetZip="$TMP_DOWNLOAD_DIR/$distributionUrlName"
  cat >"$javaSource" <<-END
	public class Downloader extends java.net.Authenticator
	{
	  protected java.net.PasswordAuthentication getPasswordAuthentication()
	  {
	    return new java.net.PasswordAuthentication( System.getenv( "MVNW_USERNAME" ), System.getenv( "MVNW_PASSWORD" ).toCharArray() );
	  }
	  public static void main( String[] args ) throws Exception
	  {
	    setDefault( new Downloader() );
	    java.nio.file.Files.copy( java.net.URI.create( args[0] ).toURL().openStream(), java.nio.file.Paths.get( args[1] ).toAbsolutePath().normalize() );
	  }
	}
	END
  # For Cygwin/MinGW, switch paths to Windows format before running javac and java
  verbose " - Compiling Downloader.java ..."
  "$(native_path "$JAVACCMD")" "$(native_path "$javaSource")" || die "Failed to compile Downloader.java"
  verbose " - Running Downloader.java ..."
  "$(native_path "$JAVACMD")" -cp "$(native_path "$TMP_DOWNLOAD_DIR")" Downloader "$distributionUrl" "$(native_path "$targetZip")"
fi

# If specified, validate the SHA-256 sum of the Maven distribution zip file
if [ -n "${distributionSha256Sum-}" ]; then
  distributionSha256Result=false
  if [ "$MVN_CMD" = mvnd.sh ]; then
    echo "Checksum validation is not supported for maven-mvnd." >&2
    echo "Please disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties." >&2
    exit 1
  elif command -v sha256sum >/dev/null; then
    if echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | sha256sum -c - >/dev/null 2>&1; then
      distributionSha256Result=true
    fi
  elif command -v shasum >/dev/null; then
    if echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | shasum -a 256 -c >/dev/null 2>&1; then
      distributionSha256Result=true
    fi
  else
    echo "Checksum validation was requested but neither 'sha256sum' or 'shasum' are available." >&2
    echo "Please install either command, or disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties." >&2
    exit 1
  fi
  if [ $distributionSha256Result = false ]; then
    echo "Error: Failed to validate Maven distribution SHA-256, your Maven distribution might be compromised." >&2
    echo "If you updated your Maven version, you need to update the specified distributionSha256Sum property." >&2
    exit 1
  fi
fi

# unzip and move
if command -v unzip >/dev/null; then
  unzip ${__MVNW_QUIET_UNZIP:+"$__MVNW_QUIET_UNZIP"} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -d "$TMP_DOWNLOAD_DIR" || die "failed to unzip"
else
  tar xzf${__MVNW_QUIET_TAR:+"$__MVNW_QUIET_TAR"} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -C "$TMP_DOWNLOAD_DIR" || die "failed to untar"
fi

# Find the actual extracted directory name (handles snapshots where filename != directory name)
actualDistributionDir=""

# First try the expected directory name (for regular distributions)
if [ -d "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain" ]; then
  if [ -f "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain/bin/$MVN_CMD" ]; then
    actualDistributionDir="$distributionUrlNameMain"
  fi
fi

# If not found, search for any directory with the Maven executable (for snapshots)
if [ -z "$actualDistributionDir" ]; then
  # enable globbing to iterate over items
  set +f
  for dir in "$TMP_DOWNLOAD_DIR"/*; do
    if [ -d "$dir" ]; then
      if [ -f "$dir/bin/$MVN_CMD" ]; then
        actualDistributionDir="$(basename "$dir")"
        break
      fi
    fi
  done
  set -f
fi

if [ -z "$actualDistributionDir" ]; then
  verbose "Contents of $TMP_DOWNLOAD_DIR:"
  verbose "$(ls -la "$TMP_DOWNLOAD_DIR")"
  die "Could not find Maven distribution directory in extracted archive"
fi

verbose "Found extracted Maven distribution directory: $actualDistributionDir"
printf %s\\n "$distributionUrl" >"$TMP_DOWNLOAD_DIR/$actualDistributionDir/mvnw.url"
mv -- "$TMP_DOWNLOAD_DIR/$actualDistributionDir" "$MAVEN_HOME" || [ -d "$MAVEN_HOME" ] || die "fail to move MAVEN_HOME"

clean || :
exec_maven "$@"

```

### 文件路径: `mvnw.cmd`
```text
<# : batch portion
@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.3.4
@REM
@REM Optional ENV vars
@REM   MVNW_REPOURL - repo url base for downloading maven distribution
@REM   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
@REM   MVNW_VERBOSE - true: enable verbose log; others: silence the output
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
@SET __MVNW_CMD__=
@SET __MVNW_ERROR__=
@SET __MVNW_PSMODULEP_SAVE=%PSModulePath%
@SET PSModulePath=
@FOR /F "usebackq tokens=1* delims==" %%A IN (`powershell -noprofile "& {$scriptDir='%~dp0'; $script='%__MVNW_ARG0_NAME__%'; icm -ScriptBlock ([Scriptblock]::Create((Get-Content -Raw '%~f0'))) -NoNewScope}"`) DO @(
  IF "%%A"=="MVN_CMD" (set __MVNW_CMD__=%%B) ELSE IF "%%B"=="" (echo %%A) ELSE (echo %%A=%%B)
)
@SET PSModulePath=%__MVNW_PSMODULEP_SAVE%
@SET __MVNW_PSMODULEP_SAVE=
@SET __MVNW_ARG0_NAME__=
@SET MVNW_USERNAME=
@SET MVNW_PASSWORD=
@IF NOT "%__MVNW_CMD__%"=="" ("%__MVNW_CMD__%" %*)
@echo Cannot start maven from wrapper >&2 && exit /b 1
@GOTO :EOF
: end batch / begin powershell #>

$ErrorActionPreference = "Stop"
if ($env:MVNW_VERBOSE -eq "true") {
  $VerbosePreference = "Continue"
}

# calculate distributionUrl, requires .mvn/wrapper/maven-wrapper.properties
$distributionUrl = (Get-Content -Raw "$scriptDir/.mvn/wrapper/maven-wrapper.properties" | ConvertFrom-StringData).distributionUrl
if (!$distributionUrl) {
  Write-Error "cannot read distributionUrl property in $scriptDir/.mvn/wrapper/maven-wrapper.properties"
}

switch -wildcard -casesensitive ( $($distributionUrl -replace '^.*/','') ) {
  "maven-mvnd-*" {
    $USE_MVND = $true
    $distributionUrl = $distributionUrl -replace '-bin\.[^.]*$',"-windows-amd64.zip"
    $MVN_CMD = "mvnd.cmd"
    break
  }
  default {
    $USE_MVND = $false
    $MVN_CMD = $script -replace '^mvnw','mvn'
    break
  }
}

# apply MVNW_REPOURL and calculate MAVEN_HOME
# maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
if ($env:MVNW_REPOURL) {
  $MVNW_REPO_PATTERN = if ($USE_MVND -eq $False) { "/org/apache/maven/" } else { "/maven/mvnd/" }
  $distributionUrl = "$env:MVNW_REPOURL$MVNW_REPO_PATTERN$($distributionUrl -replace "^.*$MVNW_REPO_PATTERN",'')"
}
$distributionUrlName = $distributionUrl -replace '^.*/',''
$distributionUrlNameMain = $distributionUrlName -replace '\.[^.]*$','' -replace '-bin$',''

$MAVEN_M2_PATH = "$HOME/.m2"
if ($env:MAVEN_USER_HOME) {
  $MAVEN_M2_PATH = "$env:MAVEN_USER_HOME"
}

if (-not (Test-Path -Path $MAVEN_M2_PATH)) {
    New-Item -Path $MAVEN_M2_PATH -ItemType Directory | Out-Null
}

$MAVEN_WRAPPER_DISTS = $null
if ((Get-Item $MAVEN_M2_PATH).Target[0] -eq $null) {
  $MAVEN_WRAPPER_DISTS = "$MAVEN_M2_PATH/wrapper/dists"
} else {
  $MAVEN_WRAPPER_DISTS = (Get-Item $MAVEN_M2_PATH).Target[0] + "/wrapper/dists"
}

$MAVEN_HOME_PARENT = "$MAVEN_WRAPPER_DISTS/$distributionUrlNameMain"
$MAVEN_HOME_NAME = ([System.Security.Cryptography.SHA256]::Create().ComputeHash([byte[]][char[]]$distributionUrl) | ForEach-Object {$_.ToString("x2")}) -join ''
$MAVEN_HOME = "$MAVEN_HOME_PARENT/$MAVEN_HOME_NAME"

if (Test-Path -Path "$MAVEN_HOME" -PathType Container) {
  Write-Verbose "found existing MAVEN_HOME at $MAVEN_HOME"
  Write-Output "MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"
  exit $?
}

if (! $distributionUrlNameMain -or ($distributionUrlName -eq $distributionUrlNameMain)) {
  Write-Error "distributionUrl is not valid, must end with *-bin.zip, but found $distributionUrl"
}

# prepare tmp dir
$TMP_DOWNLOAD_DIR_HOLDER = New-TemporaryFile
$TMP_DOWNLOAD_DIR = New-Item -Itemtype Directory -Path "$TMP_DOWNLOAD_DIR_HOLDER.dir"
$TMP_DOWNLOAD_DIR_HOLDER.Delete() | Out-Null
trap {
  if ($TMP_DOWNLOAD_DIR.Exists) {
    try { Remove-Item $TMP_DOWNLOAD_DIR -Recurse -Force | Out-Null }
    catch { Write-Warning "Cannot remove $TMP_DOWNLOAD_DIR" }
  }
}

New-Item -Itemtype Directory -Path "$MAVEN_HOME_PARENT" -Force | Out-Null

# Download and Install Apache Maven
Write-Verbose "Couldn't find MAVEN_HOME, downloading and installing it ..."
Write-Verbose "Downloading from: $distributionUrl"
Write-Verbose "Downloading to: $TMP_DOWNLOAD_DIR/$distributionUrlName"

$webclient = New-Object System.Net.WebClient
if ($env:MVNW_USERNAME -and $env:MVNW_PASSWORD) {
  $webclient.Credentials = New-Object System.Net.NetworkCredential($env:MVNW_USERNAME, $env:MVNW_PASSWORD)
}
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
$webclient.DownloadFile($distributionUrl, "$TMP_DOWNLOAD_DIR/$distributionUrlName") | Out-Null

# If specified, validate the SHA-256 sum of the Maven distribution zip file
$distributionSha256Sum = (Get-Content -Raw "$scriptDir/.mvn/wrapper/maven-wrapper.properties" | ConvertFrom-StringData).distributionSha256Sum
if ($distributionSha256Sum) {
  if ($USE_MVND) {
    Write-Error "Checksum validation is not supported for maven-mvnd. `nPlease disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties."
  }
  Import-Module $PSHOME\Modules\Microsoft.PowerShell.Utility -Function Get-FileHash
  if ((Get-FileHash "$TMP_DOWNLOAD_DIR/$distributionUrlName" -Algorithm SHA256).Hash.ToLower() -ne $distributionSha256Sum) {
    Write-Error "Error: Failed to validate Maven distribution SHA-256, your Maven distribution might be compromised. If you updated your Maven version, you need to update the specified distributionSha256Sum property."
  }
}

# unzip and move
Expand-Archive "$TMP_DOWNLOAD_DIR/$distributionUrlName" -DestinationPath "$TMP_DOWNLOAD_DIR" | Out-Null

# Find the actual extracted directory name (handles snapshots where filename != directory name)
$actualDistributionDir = ""

# First try the expected directory name (for regular distributions)
$expectedPath = Join-Path "$TMP_DOWNLOAD_DIR" "$distributionUrlNameMain"
$expectedMvnPath = Join-Path "$expectedPath" "bin/$MVN_CMD"
if ((Test-Path -Path $expectedPath -PathType Container) -and (Test-Path -Path $expectedMvnPath -PathType Leaf)) {
  $actualDistributionDir = $distributionUrlNameMain
}

# If not found, search for any directory with the Maven executable (for snapshots)
if (!$actualDistributionDir) {
  Get-ChildItem -Path "$TMP_DOWNLOAD_DIR" -Directory | ForEach-Object {
    $testPath = Join-Path $_.FullName "bin/$MVN_CMD"
    if (Test-Path -Path $testPath -PathType Leaf) {
      $actualDistributionDir = $_.Name
    }
  }
}

if (!$actualDistributionDir) {
  Write-Error "Could not find Maven distribution directory in extracted archive"
}

Write-Verbose "Found extracted Maven distribution directory: $actualDistributionDir"
Rename-Item -Path "$TMP_DOWNLOAD_DIR/$actualDistributionDir" -NewName $MAVEN_HOME_NAME | Out-Null
try {
  Move-Item -Path "$TMP_DOWNLOAD_DIR/$MAVEN_HOME_NAME" -Destination $MAVEN_HOME_PARENT | Out-Null
} catch {
  if (! (Test-Path -Path "$MAVEN_HOME" -PathType Container)) {
    Write-Error "fail to move MAVEN_HOME"
  }
} finally {
  try { Remove-Item $TMP_DOWNLOAD_DIR -Recurse -Force | Out-Null }
  catch { Write-Warning "Cannot remove $TMP_DOWNLOAD_DIR" }
}

Write-Output "MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"

```

### 文件路径: `pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.express</groupId>
    <artifactId>server-manager-tool</artifactId>
    <version>1.0.0</version>
    <name></name>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <javafx.version>21</javafx.version>
        <jsch.version>0.1.55</jsch.version>
    </properties>

    <dependencies>
        <!-- JavaFX 核心 -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <!-- SSH 库 -->
        <dependency>
            <groupId>com.jcraft</groupId>
            <artifactId>jsch</artifactId>
            <version>${jsch.version}</version>
        </dependency>
        <!-- 日志简单实现 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>2.0.9</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <configuration>
                    <mainClass>com.express.tool.MainApp</mainClass>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.6.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.express.servermanagertool.ui.MainApp</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                    <finalName>server-manager-tool</finalName>
                    <appendAssemblyId>false</appendAssemblyId>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

### 文件路径: `.mvn\wrapper\maven-wrapper.properties`
```properties
wrapperVersion=3.3.4
distributionType=only-script
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.14/apache-maven-3.9.14-bin.zip

```

### 文件路径: `src\main\java\com\express\servermanagertool\Launcher.java`
```java
package com.express.servermanagertool;

import com.express.servermanagertool.ui.MainApp;

public class Launcher {
    public static void main(String[] args) {
        MainApp.main(args);  // 直接调用原有 main 方法
    }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\core\TerminalSession.java`
```java
package com.express.servermanagertool.core;

import com.express.servermanagertool.model.Credential;
import com.express.servermanagertool.model.FileInfo;
import com.jcraft.jsch.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TerminalSession {
    private Session session;
    private ChannelShell shellChannel;
    private InputStream in;
    private OutputStream out;
    private JSch jsch = new JSch();

    public void connect(Credential cred) throws JSchException, IOException {
        session = jsch.getSession(cred.getUsername(), cred.getIp(), 22);
        session.setPassword(cred.getPassword());
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect(10000);

        shellChannel = (ChannelShell) session.openChannel("shell");
        in = shellChannel.getInputStream();
        out = shellChannel.getOutputStream();
        shellChannel.connect();
    }

    // ==================== SFTP 文件操作 ====================

    /** 获取远程当前工作目录 */
    public String getRemoteWorkDir() throws JSchException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            return sftp.pwd();
        } catch (SftpException e) {
            throw new RuntimeException(e);
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 列出指定目录下的文件/文件夹 */
    public List<FileInfo> listFiles(String remotePath) throws JSchException, SftpException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            @SuppressWarnings("unchecked")
            Vector<ChannelSftp.LsEntry> entries = sftp.ls(remotePath);
            List<FileInfo> files = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for (ChannelSftp.LsEntry entry : entries) {
                String name = entry.getFilename();
                if (".".equals(name) || "..".equals(name)) continue;
                boolean isDir = entry.getAttrs().isDir();
                long size = entry.getAttrs().getSize();
                String mtime = sdf.format(new Date(entry.getAttrs().getMTime() * 1000L));
                String perms = entry.getAttrs().getPermissionsString();
                files.add(new FileInfo(name, combinePath(remotePath, name), isDir, size, mtime, perms));
            }
            return files;
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 创建目录 */
    public void createDirectory(String remotePath) throws JSchException, SftpException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.mkdir(remotePath);
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 删除文件或空目录 */
    public void delete(String remotePath) throws JSchException, SftpException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            // 先判断是目录还是文件
            try {
                SftpATTRS attrs = sftp.stat(remotePath);
                if (attrs.isDir()) {
                    sftp.rmdir(remotePath);
                } else {
                    sftp.rm(remotePath);
                }
            } catch (SftpException e) {
                throw e;
            }
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 上传文件（已存在） */
    public void uploadFile(File localFile, String remoteDir) throws Exception {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.cd(remoteDir);
            try (FileInputStream fis = new FileInputStream(localFile)) {
                sftp.put(fis, localFile.getName());
            }
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    /** 下载文件到本地输出流 */
    public void downloadFile(String remotePath, OutputStream localOutput) throws JSchException, SftpException, IOException {
        ChannelSftp sftp = null;
        try {
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.get(remotePath, localOutput);
        } finally {
            if (sftp != null) sftp.disconnect();
        }
    }

    // 路径拼接工具
    private String combinePath(String dir, String name) {
        if (dir.equals("/")) return "/" + name;
        return dir + "/" + name;
    }

    // 原有终端相关方法
    public InputStream getInputStream() { return in; }
    public void sendCommand(String cmd) throws Exception {
        out.write((cmd + "\n").getBytes());
        out.flush();
    }

    public boolean isConnected() {
        return shellChannel != null && shellChannel.isConnected();
    }

    public void disconnect() {
        if (shellChannel != null) shellChannel.disconnect();
        if (session != null) session.disconnect();
    }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\model\Credential.java`
```java
package com.express.servermanagertool.model;

import java.io.Serializable;

public class Credential implements Serializable {
    private String ip;
    private String username;
    private String password;

    public Credential(String ip, String username, String password) {
        this.ip = ip;
        this.username = username;
        this.password = password;
    }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\model\FileInfo.java`
```java
package com.express.servermanagertool.model;

public class FileInfo {
    private String name;
    private String fullPath;
    private boolean isDirectory;
    private long size;
    private String mtime;      // 格式化的修改时间
    private String permissions;

    public FileInfo(String name, String fullPath, boolean isDirectory, long size, String mtime, String permissions) {
        this.name = name;
        this.fullPath = fullPath;
        this.isDirectory = isDirectory;
        this.size = size;
        this.mtime = mtime;
        this.permissions = permissions;
    }

    public String getName() { return name; }
    public String getFullPath() { return fullPath; }
    public boolean isDirectory() { return isDirectory; }
    public long getSize() { return size; }
    public String getMtime() { return mtime; }
    public String getPermissions() { return permissions; }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\ui\MainApp.java`
```java
package com.express.servermanagertool.ui;


import com.express.servermanagertool.util.ConfigManager;
import com.express.servermanagertool.model.Credential;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private SshTerminal terminal;
    private Credential currentCredential;
    private Stage primaryStage;
    private boolean fullScreen = false;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("");
        stage.getIcons().clear();
        stage.setWidth(900);
        stage.setHeight(600);

        // 1. 先创建一个空白的 BorderPane 作为 Scene 的根
        BorderPane placeholder = new BorderPane();
        Scene scene = new Scene(placeholder, 900, 600);

        // 设置全屏快捷键 F11
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F11) {
                toggleFullScreen();
            }
        });
        stage.setScene(scene);

        // 2. 加载凭证，决定显示哪个面板
        currentCredential = ConfigManager.load();
        if (currentCredential == null) {
            showModifyPanel();
        } else {
            showTerminalAndConnect();
        }

        stage.show();
    }

    private void showTerminalAndConnect() {
        terminal = new SshTerminal();
        terminal.connect(currentCredential, this::onDisconnected);
        BorderPane root = new BorderPane();
        root.setCenter(terminal);
        // 顶部工具栏
        ToolBar toolBar = new ToolBar();
        Button modifyBtn = new Button("✏️ 修改连接信息");
        modifyBtn.setOnAction(e -> showModifyPanel());
        Label info = new Label("当前服务器: " + currentCredential.getIp() + " | 用户: " + currentCredential.getUsername());
        toolBar.getItems().addAll(modifyBtn, new Separator(), info);
        root.setTop(toolBar);
        primaryStage.getScene().setRoot(root);
    }

    private void showModifyPanel() {
        // 断开已有连接
        if (terminal != null) terminal.disconnect();

        VBox modifyBox = new VBox(15);
        modifyBox.setStyle("-fx-padding: 30; -fx-alignment: center;");
        TextField ipField = new TextField();
        ipField.setPromptText("服务器 IP");
        TextField userField = new TextField();
        userField.setPromptText("用户名");
        PasswordField passField = new PasswordField();
        passField.setPromptText("密码");
        if (currentCredential != null) {
            ipField.setText(currentCredential.getIp());
            userField.setText(currentCredential.getUsername());
            passField.setText(currentCredential.getPassword());
        }
        Button saveBtn = new Button("保存并连接");
        saveBtn.setOnAction(e -> {
            String ip = ipField.getText().trim();
            String user = userField.getText().trim();
            String pass = passField.getText();
            if (ip.isEmpty() || user.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "IP和用户名不能为空");
                alert.showAndWait();
                return;
            }
            Credential newCred = new Credential(ip, user, pass);
            try {
                com.express.servermanagertool.util.ConfigManager.save(newCred);
                currentCredential = newCred;
                showTerminalAndConnect();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "保存凭证失败: " + ex.getMessage());
                alert.showAndWait();
            }
        });
        Button cancelBtn = new Button("取消");
        cancelBtn.setOnAction(e -> {
            if (currentCredential != null) {
                showTerminalAndConnect();
            } else {
                Platform.exit();
            }
        });
        modifyBox.getChildren().addAll(
                new Label("请输入服务器连接信息："), ipField, userField, passField,
                new HBox(10, saveBtn, cancelBtn)
        );
        primaryStage.getScene().setRoot(modifyBox);
    }

    private void onDisconnected() {
        // 会话意外断开，弹出提示并切换到修改面板
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SSH 连接已断开，请修改信息后重新连接。");
            alert.showAndWait();
            showModifyPanel();
        });
    }

    private void toggleFullScreen() {
        fullScreen = !fullScreen;
        primaryStage.setFullScreen(fullScreen);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\ui\RemoteFileBrowser.java`
```java
package com.express.servermanagertool.ui;

import com.express.servermanagertool.core.TerminalSession;
import com.express.servermanagertool.model.FileInfo;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class RemoteFileBrowser extends BorderPane {
    private final TerminalSession session;
    private final Runnable onExit;

    private TreeView<String> treeView;
    private TableView<FileInfo> fileTable;
    private Label pathLabel;
    private String currentPath = "/";

    public RemoteFileBrowser(TerminalSession session, Runnable onExit) {
        this.session = session;
        this.onExit = onExit;
        buildUI();
        // 启动时切换到远程家目录
        new Thread(() -> {
            try {
                String home = session.getRemoteWorkDir();
                if (home != null && !home.isEmpty()) currentPath = home;
            } catch (Exception ignored) {}
            Platform.runLater(() -> loadDirectory(currentPath));
        }).start();
    }

    @SuppressWarnings("unchecked")
    private void buildUI() {
        // ================= 全局深色背景 =================
        setStyle("-fx-background-color: #202020;");

        // ================= 顶部栏：路径 + 按钮 + 退出 =================
        HBox topBar = new HBox(6);
        topBar.setStyle("-fx-background-color: #2d2d2d; -fx-padding: 6 10; -fx-alignment: center-left;");

        // 路径标签
        pathLabel = new Label("/");
        pathLabel.setStyle(
                "-fx-background-color: #1e1e1e; -fx-text-fill: #e0e0e0; " +
                        "-fx-font-family: 'monospace'; -fx-font-size: 13; " +
                        "-fx-border-color: #555; -fx-border-radius: 3; -fx-padding: 4 10;"
        );
        HBox.setHgrow(pathLabel, Priority.ALWAYS);

        // 工具栏按钮（扁平样式）
        String btnBase = "-fx-background-color: transparent; -fx-text-fill: #ccc; " +
                "-fx-font-size: 12; -fx-padding: 6 10; -fx-border-radius: 3;";

        Button refreshBtn = styledButton("🔄 刷新", btnBase);
        Button upBtn = styledButton("⬆ 向上", btnBase);
        Button mkdirBtn = styledButton("📁⁺ 新建文件夹", btnBase);
        Button deleteBtn = styledButton("🗑 删除", btnBase);
        Button downloadBtn = styledButton("⬇ 下载", btnBase);
        Button uploadBtn = styledButton("⬆ 上传", btnBase);

        // 退出按钮
        Button exitBtn = new Button("✕ 退出");
        exitBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6 12;");

        topBar.getChildren().addAll(pathLabel, refreshBtn, upBtn, mkdirBtn,
                deleteBtn, downloadBtn, uploadBtn, exitBtn);
        setTop(topBar);

        // ================= 左侧目录树 =================
        treeView = new TreeView<>();
        TreeItem<String> rootItem = new TreeItem<>("/");
        rootItem.getChildren().add(new TreeItem<>("")); // 占位用于展开
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true);
        treeView.setStyle(
                "-fx-background-color: #252526; -fx-text-fill: #e0e0e0; " +
                        "-fx-font-size: 13; -fx-border-color: #444;"
        );
        treeView.setCellFactory(tv -> new TreeCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String name = item.equals("/") ? "/" : item.substring(item.lastIndexOf('/') + 1);
                    setText(name);
                }
            }
        });
        // 点击树节点 → 加载目录
        treeView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.getValue() != null) {
                loadDirectory(newVal.getValue());
            }
        });
        // 展开时懒加载子目录
        treeView.getRoot().addEventHandler(TreeItem.branchExpandedEvent(), e -> {
            TreeItem<?> source = e.getTreeItem();
            if (source.getValue() instanceof String) {
                TreeItem<String> item = (TreeItem<String>) source;
                if (!item.isLeaf() && item.getChildren().size() == 1 && "".equals(item.getChildren().get(0).getValue())) {
                    loadSubDirectories(item);
                }
            }
        });

        // ================= 右侧文件表格 =================
        fileTable = new TableView<>();
        fileTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fileTable.setStyle("-fx-background-color: #202020; -fx-text-fill: #e0e0e0; -fx-font-size: 13;");

        TableColumn<FileInfo, String> nameCol = new TableColumn<>("名称");
        nameCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getName()));
        TableColumn<FileInfo, String> sizeCol = new TableColumn<>("大小");
        sizeCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(formatSize(cd.getValue().getSize())));
        TableColumn<FileInfo, String> timeCol = new TableColumn<>("修改时间");
        timeCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getMtime()));
        TableColumn<FileInfo, String> permCol = new TableColumn<>("权限");
        permCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getPermissions()));

        fileTable.getColumns().addAll(nameCol, sizeCol, timeCol, permCol);
        // 双击进入文件夹
        fileTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                FileInfo selected = fileTable.getSelectionModel().getSelectedItem();
                if (selected != null && selected.isDirectory()) {
                    loadDirectory(selected.getFullPath());
                }
            }
        });

        // ================= 分割容器（可拖动） =================
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(treeView, fileTable);
        splitPane.setDividerPositions(0.25);
        splitPane.setStyle("-fx-background-color: #202020; -fx-border-color: #444;");
        setCenter(splitPane);

        // ================= 按钮事件绑定 =================
        refreshBtn.setOnAction(e -> loadDirectory(currentPath));
        upBtn.setOnAction(e -> {
            if (!"/".equals(currentPath)) {
                currentPath = currentPath.substring(0, currentPath.lastIndexOf('/'));
                if (currentPath.isEmpty()) currentPath = "/";
                loadDirectory(currentPath);
            }
        });
        mkdirBtn.setOnAction(e -> showCreateDirectoryDialog());
        deleteBtn.setOnAction(e -> {
            FileInfo selected = fileTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showWarning("请先选择要删除的文件或目录");
                return;
            }
            confirmAndDelete(selected);
        });
        downloadBtn.setOnAction(e -> {
            FileInfo selected = fileTable.getSelectionModel().getSelectedItem();
            if (selected == null || selected.isDirectory()) {
                showWarning("请选择一个文件进行下载");
                return;
            }
            downloadFile(selected);
        });
        uploadBtn.setOnAction(e -> uploadFile());
        exitBtn.setOnAction(e -> onExit.run());
    }

    // 辅助方法：创建带悬停样式的按钮
    private Button styledButton(String text, String baseStyle) {
        Button btn = new Button(text);
        btn.setStyle(baseStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(baseStyle + "-fx-background-color: #454545; -fx-text-fill: white;"));
        btn.setOnMouseExited(e -> btn.setStyle(baseStyle));
        return btn;
    }

    // ==================== 核心逻辑 ====================
    private void loadDirectory(String path) {
        currentPath = path;
        pathLabel.setText(path);
        new Thread(() -> {
            try {
                List<FileInfo> files = session.listFiles(path);
                Platform.runLater(() -> {
                    fileTable.getItems().setAll(files);
                    selectTreeByPath(path);
                });
            } catch (Exception e) {
                Platform.runLater(() -> showError("列出目录失败: " + e.getMessage()));
            }
        }).start();
    }

    private void loadSubDirectories(TreeItem<String> parent) {
        new Thread(() -> {
            try {
                List<FileInfo> items = session.listFiles(parent.getValue());
                Platform.runLater(() -> {
                    parent.getChildren().clear();
                    for (FileInfo f : items) {
                        if (f.isDirectory()) {
                            TreeItem<String> child = new TreeItem<>(f.getFullPath());
                            child.getChildren().add(new TreeItem<>(""));
                            parent.getChildren().add(child);
                        }
                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> showError("加载子目录失败: " + e.getMessage()));
            }
        }).start();
    }

    private void selectTreeByPath(String path) {
        TreeItem<String> root = treeView.getRoot();
        if (root == null) return;
        TreeItem<String> node = findNode(root, path);
        if (node != null) {
            treeView.getSelectionModel().select(node);
            node.setExpanded(true);
        }
    }

    private TreeItem<String> findNode(TreeItem<String> parent, String targetPath) {
        if (parent.getValue().equals(targetPath)) return parent;
        for (TreeItem<String> child : parent.getChildren()) {
            TreeItem<String> found = findNode(child, targetPath);
            if (found != null) return found;
        }
        return null;
    }

    // ==================== 文件操作 ====================
    private void showCreateDirectoryDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("新建文件夹");
        dialog.setHeaderText("在 " + currentPath + " 创建新目录");
        dialog.setContentText("名称:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> new Thread(() -> {
            try {
                String newPath = currentPath.equals("/") ? "/" + name : currentPath + "/" + name;
                session.createDirectory(newPath);
                Platform.runLater(() -> loadDirectory(currentPath));
            } catch (Exception ex) {
                Platform.runLater(() -> showError("创建失败: " + ex.getMessage()));
            }
        }).start());
    }

    private void confirmAndDelete(FileInfo target) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("确认删除");
        confirm.setHeaderText("删除 " + target.getName());
        confirm.setContentText("该操作不可恢复，确定要删除吗？");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                new Thread(() -> {
                    try {
                        session.delete(target.getFullPath());
                        Platform.runLater(() -> loadDirectory(currentPath));
                    } catch (Exception ex) {
                        Platform.runLater(() -> showError("删除失败: " + ex.getMessage()));
                    }
                }).start();
            }
        });
    }

    private void downloadFile(FileInfo file) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(file.getName());
        File target = fileChooser.showSaveDialog(getScene().getWindow());
        if (target != null) {
            new Thread(() -> {
                try (FileOutputStream fos = new FileOutputStream(target)) {
                    session.downloadFile(file.getFullPath(), fos);
                    Platform.runLater(() -> showInfo("下载完成"));
                } catch (Exception ex) {
                    Platform.runLater(() -> showError("下载失败: " + ex.getMessage()));
                }
            }).start();
        }
    }

    private void uploadFile() {
        FileChooser fileChooser = new FileChooser();
        File localFile = fileChooser.showOpenDialog(getScene().getWindow());
        if (localFile != null) {
            new Thread(() -> {
                try {
                    session.uploadFile(localFile, currentPath);
                    Platform.runLater(() -> loadDirectory(currentPath));
                } catch (Exception ex) {
                    Platform.runLater(() -> showError("上传失败: " + ex.getMessage()));
                }
            }).start();
        }
    }

    // ==================== 工具方法 ====================
    private String formatSize(long bytes) {
        if (bytes < 0) return "";
        if (bytes == 0) return "0 B";
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int digit = (int) (Math.log10(bytes) / Math.log10(1024));
        return String.format("%.1f %s", bytes / Math.pow(1024, digit), units[digit]);
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }

    private void showWarning(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
    }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\ui\SshTerminal.java`
```java
package com.express.servermanagertool.ui;

import com.express.servermanagertool.model.Credential;
import com.express.servermanagertool.core.TerminalSession;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SshTerminal extends BorderPane {
    private final TextArea outputArea;
    private final TextField inputField;
    private TerminalSession session;
    private final List<String> history = new ArrayList<>();
    private int historyIndex = 0;
    private volatile boolean isConnected = false;
    private Runnable onDisconnect;

    // 用于存放终端部分的容器（输出区 + 输入行），方便整体替换
    private BorderPane terminalPanel;
    private RemoteFileBrowser fileBrowser;
    private boolean isFileBrowserActive = false;

    public SshTerminal() {
        // 终端区域
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setStyle("-fx-font-family: 'Courier New', monospace; -fx-font-size: 13px; " +
                "-fx-control-inner-background: #0c0c10; -fx-text-fill: #cbd5e6; " +
                "-fx-padding: 10; -fx-background-color: #0c0c10;");

        inputField = new TextField();
        inputField.setStyle("-fx-font-family: monospace; -fx-background-color: #0c0c10; " +
                "-fx-text-fill: #f1f5f9; -fx-padding: 5;");

        HBox inputBox = new HBox(0);
        inputBox.setStyle("-fx-background-color: #0c0c10; -fx-padding: 0 10 10 10;");
        Label prompt = new Label("$ ");
        prompt.setStyle("-fx-text-fill: #4ade80; -fx-font-weight: bold;");
        inputBox.getChildren().addAll(prompt, inputField);
        HBox.setHgrow(inputField, Priority.ALWAYS);

        // 用一个 BorderPane 包装输出区和输入区
        terminalPanel = new BorderPane();
        terminalPanel.setCenter(outputArea);
        terminalPanel.setBottom(inputBox);
        terminalPanel.setStyle("-fx-background-color: #0c0c10;");

        setCenter(terminalPanel);
        this.setStyle("-fx-background-color: #0c0c10;");

        setupKeyEvents();
        setupDragAndDrop();
    }

    private void setupKeyEvents() {
        inputField.setOnAction(e -> sendCommand());
        inputField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP && historyIndex > 0) {
                inputField.setText(history.get(--historyIndex));
            } else if (e.getCode() == KeyCode.DOWN) {
                if (historyIndex < history.size() - 1) {
                    inputField.setText(history.get(++historyIndex));
                } else {
                    historyIndex = history.size();
                    inputField.clear();
                }
            }
        });
    }

    private void setupDragAndDrop() {
        this.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles() && isConnected) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        this.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                handleUpload(db.getFiles());
            }
            event.setDropCompleted(true);
            event.consume();
        });
    }

    private void handleUpload(List<File> files) {
        if (!isConnected || session == null) return;
        new Thread(() -> {
            try {
                String remotePath = session.getRemoteWorkDir();
                if (remotePath == null || remotePath.trim().isEmpty()) {
                    throw new Exception("无法获取远程工作目录");
                }
                for (File f : files) {
                    final String fileName = f.getName();
                    Platform.runLater(() ->
                            outputArea.appendText("\n[System] 正在上传: " + fileName + " -> " + remotePath + "\n")
                    );
                    session.uploadFile(f, remotePath);
                    Platform.runLater(() ->
                            outputArea.appendText("[System] " + fileName + " 上传成功!\n")
                    );
                }
            } catch (Exception e) {
                Platform.runLater(() ->
                        outputArea.appendText("\n[Error] 上传失败: " + e.getMessage() + "\n")
                );
            }
        }).start();
    }

    public void connect(Credential cred, Runnable onDisconnectCallback) {
        this.onDisconnect = onDisconnectCallback;
        new Thread(() -> {
            try {
                session = new TerminalSession();
                session.connect(cred);
                isConnected = true;
                Platform.runLater(() -> {
                    outputArea.clear();
                    outputArea.appendText("[System] 连接成功! （输入 view 打开可视化文件管理）\n");
                    inputField.requestFocus();
                });
                startReader();
            } catch (Exception e) {
                Platform.runLater(() -> {
                    outputArea.appendText("[System] 连接失败: " + e.getMessage() + "\n");
                    if (onDisconnect != null) onDisconnect.run();
                });
            }
        }).start();
    }

    private void startReader() {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(session.getInputStream()))) {
                char[] buf = new char[1024];
                int len;
                while (isConnected && (len = reader.read(buf)) != -1) {
                    String chunk = new String(buf, 0, len);
                    Platform.runLater(() -> {
                        outputArea.appendText(chunk);
                        outputArea.positionCaret(outputArea.getLength());
                    });
                }
            } catch (Exception e) {
                if (isConnected) disconnect();
            }
        }).start();
    }

    private void sendCommand() {
        String cmd = inputField.getText().trim();
        if (cmd.isEmpty() || !isConnected) return;
        // 特殊命令 view：切换到文件管理器
        if ("view".equalsIgnoreCase(cmd)) {
            inputField.clear();
            showFileBrowser();
            return;
        }
        history.add(cmd);
        historyIndex = history.size();
        try {
            session.sendCommand(cmd);
        } catch (Exception e) {
            outputArea.appendText("[Error] " + e.getMessage() + "\n");
        }
        inputField.clear();
    }

    private void showFileBrowser() {
        if (!isConnected || session == null) return;
        if (fileBrowser == null) {
            fileBrowser = new RemoteFileBrowser(session, this::showTerminal);
        }
        isFileBrowserActive = true;
        setCenter(fileBrowser);
    }

    private void showTerminal() {
        isFileBrowserActive = false;
        setCenter(terminalPanel);
        inputField.requestFocus();
    }

    public void disconnect() {
        isConnected = false;
        if (session != null) {
            session.disconnect();
            session = null;
        }
        Platform.runLater(() -> {
            inputField.setDisable(true);
            if (onDisconnect != null) onDisconnect.run();
        });
    }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\util\ConfigManager.java`
```java
package com.express.servermanagertool.util;

import com.express.servermanagertool.model.Credential;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private static final String CONFIG_FILE = System.getProperty("user.home") +
            File.separator + ".server_manager.conf";

    public static void save(Credential cred) throws IOException {
        Properties props = new Properties();
        props.setProperty("ip", cred.getIp());
        props.setProperty("username", cred.getUsername());
        props.setProperty("password", cred.getPassword());
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            props.store(fos, "Server Manager Credentials");
        }
    }

    public static Credential load() {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) return null;
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis);
            String ip = props.getProperty("ip", "");
            String username = props.getProperty("username", "");
            String password = props.getProperty("password", "");
            if (ip.isEmpty() || username.isEmpty()) return null;
            return new Credential(ip, username, password);
        } catch (IOException ignored) {
            return null;
        }
    }
}
```

### 文件路径: `src\main\resources\application.properties`
```properties
spring.application.name=server-manager-tool

```

### 文件路径: `src\main\resources\templates\index.html`
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>服务器管理工具</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 80px; }
        input { width: 250px; padding: 8px; }
        button { padding: 8px 20px; margin-right: 10px; cursor: pointer; }
        .message { margin-top: 20px; padding: 10px; border-radius: 4px; }
        .success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    </style>
</head>
<body>
<h2>服务器管理工具</h2>
<form id="credForm">
    <div class="form-group">
        <label>IP地址：</label>
        <input type="text" id="ip" th:value="${credential != null ? credential.ip : ''}">
    </div>
    <div class="form-group">
        <label>用户名：</label>
        <input type="text" id="username" th:value="${credential != null ? credential.username : ''}">
    </div>
    <div class="form-group">
        <label>密码：</label>
        <input type="password" id="password" th:value="${credential != null ? credential.password : ''}">
    </div>
    <div>
        <button type="button" id="saveBtn">保存修改</button>
        <button type="button" id="connectBtn">连接服务器</button>
    </div>
</form>
<div id="message" class="message" style="display:none;"></div>

<script>
    const saveBtn = document.getElementById('saveBtn');
    const connectBtn = document.getElementById('connectBtn');
    const messageDiv = document.getElementById('message');

    function showMessage(text, isError) {
        messageDiv.textContent = text;
        messageDiv.className = 'message ' + (isError ? 'error' : 'success');
        messageDiv.style.display = 'block';
        setTimeout(() => {
            messageDiv.style.display = 'none';
        }, 5000);
    }

    // 保存修改（覆盖凭证）
    saveBtn.addEventListener('click', () => {
        const ip = document.getElementById('ip').value.trim();
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value;
        if (!ip || !username || !password) {
            showMessage('请填写完整信息', true);
            return;
        }
        fetch('/api/credentials', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ ip, username, password })
        })
            .then(res => res.json())
            .then(data => {
                showMessage(data.message, false);
            })
            .catch(err => showMessage('保存失败: ' + err.message, true));
    });

    // 连接服务器（使用当前显示的凭证，因为已经保存过，服务端会使用最新保存的凭证）
    connectBtn.addEventListener('click', () => {
        // 先保存当前输入（确保服务端凭证是最新）
        const ip = document.getElementById('ip').value.trim();
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value;
        if (!ip || !username || !password) {
            showMessage('请填写完整信息', true);
            return;
        }
        // 先调用保存接口，再连接
        fetch('/api/credentials', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ ip, username, password })
        })
            .then(() => {
                // 然后测试连接
                return fetch('/api/connect', { method: 'POST' });
            })
            .then(res => res.json())
            .then(data => {
                if (data.success) {
                    showMessage(`连接成功！服务器：${data.server}`, false);
                } else {
                    showMessage(`连接失败：${data.message}`, true);
                }
            })
            .catch(err => showMessage('连接异常: ' + err.message, true));
    });
</script>
</body>
</html>
```

### 文件路径: `src\test\java\com\express\servermanagertool\ServerManagerToolApplicationTests.java`
```java
package com.express.servermanagertool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerManagerToolApplicationTests {

    @Test
    void contextLoads() {
    }

}

```

