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
    README.md
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
                                WebViewBackend.java
                                WebViewFileBrowser.java
                            util/
                                ConfigManager.java
            resources/
                application.properties
                static/
                templates/
                    index.html
                    linux_desktop.html
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
        <!-- JavaFX Web 模块（包含 WebView） -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-web</artifactId>
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

### 文件路径: `README.md`
```markdown
# 🖥️ 服务器管理工具 (Server Manager Tool)

一个基于 **JavaFX** 和 **JSch** 开发的轻量级图形化服务器管理工具，提供 **SSH 终端模拟**与 **SFTP 远程文件管理**功能，帮助开发者便捷地管理 Linux 服务器。

## ✨ 主要特性

- 🔐 **凭证管理**：首次使用时输入服务器 IP、用户名、密码，后续自动保存（本地加密存储，位于 `~/.server_manager.conf`）。
- 🖥️ **SSH 终端**：支持完整的 Shell 交互，历史命令记录（上下键切换），拖拽上传文件。
- 📂 **远程文件浏览器**：
  - 以树形目录+表格形式浏览远程文件/文件夹
  - 支持新建目录、删除、重命名（删除前确认）
  - 上传/下载文件（图形化选择本地文件）
  - 双击目录进入，支持路径导航及向上返回
- 🧩 **一体化 UI**：无边框窗口，自定义标题栏（包含服务器信息显示、修改凭证按钮、窗口控制按钮），支持 F11 全屏。
- 📁 **拖拽上传**：将本地文件直接拖入终端窗口即可上传到当前工作目录。
- 🔌 **轻量快捷**：无需配置 Web 服务，纯桌面 Java 应用，依赖少，启动快。

## 🛠️ 技术栈

| 名称          | 版本      | 用途                     |
| ------------- | --------- | ------------------------ |
| Java          | 17        | 基础运行环境             |
| JavaFX        | 21        | 桌面 UI 框架             |
| JSch          | 0.1.55    | SSH / SFTP 协议实现      |
| Maven         | 3.9.14    | 项目构建与依赖管理       |
| SLF4J Simple  | 2.0.9     | 简单日志输出             |

## 📦 如何构建与运行

### 前置条件
- JDK 17 或更高版本
- Maven 3.6+

### 构建可执行 JAR（包含所有依赖）

```bash
mvn clean compile assembly:single
```

构建成功后，在 `target/` 目录下生成 `server-manager-tool.jar`。

### 运行

```bash
java -jar target/server-manager-tool.jar
```

> 如果直接双击运行（Windows）可能因 JavaFX 模块路径问题失败，建议通过命令行启动。

## 🚀 使用说明

1. **首次启动**：弹出凭证配置窗口，填写服务器 IP、SSH 用户名及密码，点击“保存并连接”。
2. **SSH 终端**：连接成功后进入终端界面，可以执行任意 Shell 命令。
    - 输入 `view` 并回车，切换到远程文件浏览器模式。
    - 在文件浏览器中点击“✕ 退出”回到终端。
3. **文件上传**：
    - 在终端模式下，直接将文件拖入窗口，自动上传到远程当前工作目录。
    - 在文件浏览器模式下，点击“⬆ 上传”按钮选择本地文件上传。
4. **文件下载**：在文件浏览器中选中文件，点击“⬇ 下载”保存到本地。
5. **修改连接信息**：点击标题栏的“✏️ 修改”按钮，重新编辑凭证（会自动断开当前连接）。

## 📁 项目目录结构

```
.
├── .gitignore
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java/com/express/servermanagertool
│   │   │   ├── Launcher.java              # 启动器
│   │   │   ├── core/TerminalSession.java  # SSH/SFTP 核心操作
│   │   │   ├── model/                     # 数据模型（Credential, FileInfo）
│   │   │   ├── ui/                        # 界面类（MainApp, SshTerminal, RemoteFileBrowser）
│   │   │   └── util/ConfigManager.java    # 凭证持久化
│   │   └── resources/                     # 资源文件（暂未使用）
│   └── test/                              # 单元测试
```

## 💡 注意与已知限制

- **凭证存储**：密码以明文形式保存在用户目录下的 `.server_manager.conf` 文件中（Java Properties 格式），请确保操作系统文件权限安全。
- **首次运行**：如果系统未安装 JavaFX 环境，请确保 JDK 包含 JavaFX 或通过 Maven 依赖（已包含）。
- **中文支持**：终端输出支持 UTF-8，但部分远程服务器可能需配置 `LANG` 环境变量。
- **SFTP 兼容性**：基于 JSch 实现，适用于绝大多数 OpenSSH 服务器。

## 📄 开源协议

本项目采用 **MIT License** 开源，可自由使用、修改、分发。

## 🤝 作者

由 [HGJYHH](https://github.com/HGJYHH) 开发维护。

---

**Enjoy managing your servers with ease!**  
如有问题或建议，欢迎提交 Issue 或 Pull Request。
```

保存后，你可以用 `git add README.md` 把它一起提交到 GitHub，这样仓库主页就会显示一个漂亮的项目介绍了。
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

import com.express.servermanagertool.model.Credential;
import com.express.servermanagertool.util.ConfigManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    private Credential currentCredential;
    private Stage primaryStage;
    private boolean fullScreen = false;
    private double xOffset = 0, yOffset = 0;
    private SshTerminal terminal;
    private boolean isModifyPanelShowing = false;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("");
        stage.setWidth(900);
        stage.setHeight(600);

        BorderPane initialRoot = new BorderPane();
        Scene scene = new Scene(initialRoot, 900, 600);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F11) toggleFullScreen();
        });
        stage.setScene(scene);

        currentCredential = ConfigManager.load();
        if (currentCredential == null) {
            showModifyPanel();
        } else {
            showTerminalAndConnect();
        }

        stage.show();
    }

    private HBox createCustomTitleBar() {
        // ... 保持不变（与原代码相同）
        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: #2d2d2d; -fx-padding: 8 12; -fx-alignment: center-left;");
        titleBar.setPrefHeight(40);

        Label titleLabel = new Label("服务器管理工具");
        titleLabel.setStyle("-fx-text-fill: #e0e0e0; -fx-font-size: 14; -fx-font-weight: bold;");

        Label infoLabel = new Label();
        infoLabel.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 12; -fx-padding: 0 10 0 10;");
        if (currentCredential != null) {
            infoLabel.setText(currentCredential.getIp() + " | " + currentCredential.getUsername());
        }

        Button modifyBtn = new Button("✏️ 修改");
        modifyBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #4ade80; -fx-cursor: hand;");
        modifyBtn.setOnAction(e -> showModifyPanel());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button minBtn = new Button("─");
        minBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        minBtn.setOnAction(e -> primaryStage.setIconified(true));

        Button fullBtn = new Button("□");
        fullBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        fullBtn.setOnAction(e -> toggleFullScreen());

        Button closeBtn = new Button("✕");
        closeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        closeBtn.setOnAction(e -> {
            if (terminal != null) terminal.disconnect(false);
            Platform.exit();
        });

        titleBar.getChildren().addAll(titleLabel, infoLabel, modifyBtn, spacer, minBtn, fullBtn, closeBtn);

        titleBar.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        titleBar.setOnMouseDragged(event -> {
            if (!fullScreen) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        return titleBar;
    }

    private void showTerminalAndConnect() {
        terminal = new SshTerminal();
        terminal.connect(currentCredential, this::onDisconnected);

        BorderPane root = new BorderPane();
        root.setTop(createCustomTitleBar());
        root.setCenter(terminal);

        primaryStage.getScene().setRoot(root);
    }

    private void onDisconnected() {
        Platform.runLater(() -> {
            if (isModifyPanelShowing) return;
            showAlert(Alert.AlertType.WARNING, "SSH 连接已断开，请修改信息后重新连接。");
            showModifyPanel();
        });
    }

    // 美化的登录/修改面板  (Windows 11 风格深色毛玻璃)
    private void showModifyPanel() {
        if (isModifyPanelShowing) return;
        isModifyPanelShowing = true;

        if (terminal != null) {
            terminal.disconnect(false);
            terminal = null;
        }

        // 创建一个容器，用于放置整个登录界面
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: transparent;"); // 根背景透明，让底层窗口背景可见（若有）

        // 主体内容：居中一个卡片
        VBox card = new VBox(20);
        card.setMaxWidth(400);
        card.setMaxHeight(350);
        card.setStyle(
                "-fx-background-color: rgba(30, 32, 40, 0.95); " +
                        "-fx-background-radius: 16; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 20, 0.2, 0, 8); " +
                        "-fx-padding: 30 28 30 28; " +
                        "-fx-border-color: rgba(255,255,255,0.15); " +
                        "-fx-border-radius: 16;"
        );

        // 标题
        Label titleLabel = new Label("🔐 服务器认证");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20; -fx-font-weight: bold;");

        // 副标题
        Label subtitle = new Label("请输入 SSH 连接信息");
        subtitle.setStyle("-fx-text-fill: #aaa; -fx-font-size: 12;");

        // 输入框组
        VBox inputGroup = new VBox(12);
        inputGroup.setStyle("-fx-padding: 10 0 0 0;");

        // IP 输入框
        TextField ipField = new TextField();
        ipField.setPromptText("服务器 IP 地址");
        ipField.setStyle(
                "-fx-background-color: #1e1f2c; " +
                        "-fx-border-color: #3c3f4a; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-text-fill: #e0e0e0; " +
                        "-fx-prompt-text-fill: #6a6f7a; " +
                        "-fx-padding: 10 12;"
        );
        if (currentCredential != null) ipField.setText(currentCredential.getIp());

        // 用户名输入框
        TextField userField = new TextField();
        userField.setPromptText("用户名");
        userField.setStyle(ipField.getStyle());
        if (currentCredential != null) userField.setText(currentCredential.getUsername());

        // 密码输入框
        PasswordField passField = new PasswordField();
        passField.setPromptText("密码");
        passField.setStyle(ipField.getStyle());
        if (currentCredential != null) passField.setText(currentCredential.getPassword());

        inputGroup.getChildren().addAll(ipField, userField, passField);

        // 按钮区域
        HBox buttonBox = new HBox(15);
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10 0 0 0;");

        Button saveBtn = new Button("连接服务器");
        saveBtn.setStyle(
                "-fx-background-color: #0a6cff; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 20; " +
                        "-fx-padding: 8 24; " +
                        "-fx-cursor: hand;"
        );
        saveBtn.setOnMouseEntered(e -> saveBtn.setStyle(saveBtn.getStyle() + "-fx-background-color: #1a7cff;"));
        saveBtn.setOnMouseExited(e -> saveBtn.setStyle("-fx-background-color: #0a6cff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 8 24; -fx-cursor: hand;"));

        Button cancelBtn = new Button("取消");
        cancelBtn.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-text-fill: #ccc; " +
                        "-fx-border-color: #5a5f6e; " +
                        "-fx-border-radius: 20; " +
                        "-fx-padding: 8 24; " +
                        "-fx-cursor: hand;"
        );
        cancelBtn.setOnMouseEntered(e -> cancelBtn.setStyle(cancelBtn.getStyle() + "-fx-background-color: #2a2e3a;"));
        cancelBtn.setOnMouseExited(e -> cancelBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ccc; -fx-border-color: #5a5f6e; -fx-border-radius: 20; -fx-padding: 8 24; -fx-cursor: hand;"));

        buttonBox.getChildren().addAll(saveBtn, cancelBtn);

        // 将所有组件添加到卡片
        card.getChildren().addAll(titleLabel, subtitle, inputGroup, buttonBox);

        // 将卡片居中显示
        StackPane centerPane = new StackPane(card);
        centerPane.setStyle("-fx-background-color: #12151e;"); // 整个窗口背景深色

        root.setCenter(centerPane);

        // 简单的自定义标题栏（保留窗口控制）
        HBox simpleTitle = new HBox();
        simpleTitle.setStyle("-fx-background-color: #1e2028; -fx-padding: 6 12; -fx-alignment: center-right;");
        Button closeOnly = new Button("✕");
        closeOnly.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold;");
        closeOnly.setOnAction(e -> Platform.exit());
        simpleTitle.getChildren().add(closeOnly);
        root.setTop(simpleTitle);

        // 保存和取消事件
        saveBtn.setOnAction(e -> {
            String ip = ipField.getText().trim();
            String user = userField.getText().trim();
            String pass = passField.getText();
            if (ip.isEmpty() || user.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "IP 和用户名不能为空");
                return;
            }
            Credential newCred = new Credential(ip, user, pass);
            try {
                ConfigManager.save(newCred);
                currentCredential = newCred;
                isModifyPanelShowing = false;
                showTerminalAndConnect();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "保存凭证失败: " + ex.getMessage());
            }
        });

        cancelBtn.setOnAction(e -> {
            if (currentCredential != null) {
                isModifyPanelShowing = false;
                showTerminalAndConnect();
            } else {
                Platform.exit();
            }
        });

        primaryStage.getScene().setRoot(root);
    }

    private void toggleFullScreen() {
        fullScreen = !fullScreen;
        primaryStage.setFullScreen(fullScreen);
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type, msg);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
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
import javafx.scene.Node;
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

    private BorderPane terminalPanel;
    private BorderPane fileBrowser;          // 改为 BorderPane 类型，兼容 RemoteFileBrowser 和 WebViewFileBrowser
    private boolean isFileBrowserActive = false;

    public SshTerminal() {
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setStyle("-fx-font-family: 'Courier New', monospace; -fx-font-size: 13px; " +
                "-fx-control-inner-background: #0c0c10; -fx-text-fill: #cbd5e6; " +
                "-fx-padding: 10; -fx-background-color: #0c0c10;");
        // 隐藏滚动条
        Platform.runLater(() -> {
            Node scrollPane = outputArea.lookup(".scroll-pane");
            if (scrollPane instanceof ScrollPane) {
                ScrollPane sp = (ScrollPane) scrollPane;
                sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            }
        });

        inputField = new TextField();
        inputField.setStyle("-fx-font-family: monospace; -fx-background-color: #0c0c10; " +
                "-fx-text-fill: #f1f5f9; -fx-padding: 5;");

        HBox inputBox = new HBox(0);
        inputBox.setStyle("-fx-background-color: #0c0c10; -fx-padding: 0 10 10 10;");
        Label prompt = new Label("$ ");
        prompt.setStyle("-fx-text-fill: #4ade80; -fx-font-weight: bold;");
        inputBox.getChildren().addAll(prompt, inputField);
        HBox.setHgrow(inputField, Priority.ALWAYS);

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

    // 修改此处：使用 WebViewFileBrowser 代替 RemoteFileBrowser
    private void showFileBrowser() {
        if (!isConnected || session == null) return;
        if (fileBrowser == null) {
            // 原为：fileBrowser = new RemoteFileBrowser(session, this::showTerminal);
            fileBrowser = new WebViewFileBrowser(session, this::showTerminal);
        }
        isFileBrowserActive = true;
        setCenter(fileBrowser);
    }

    private void showTerminal() {
        isFileBrowserActive = false;
        setCenter(terminalPanel);
        inputField.requestFocus();
        // 可选：释放 WebViewFileBrowser 资源（如果有 dispose 方法）
        if (fileBrowser instanceof WebViewFileBrowser) {
            ((WebViewFileBrowser) fileBrowser).dispose();
        }
        fileBrowser = null;  // 下次输入 view 会重新创建
    }

    public void disconnect() {
        disconnect(true);
    }

    public void disconnect(boolean runCallback) {
        isConnected = false;
        if (session != null) {
            session.disconnect();
            session = null;
        }
        if (runCallback) {
            Platform.runLater(() -> {
                inputField.setDisable(true);
                if (onDisconnect != null) onDisconnect.run();
            });
        }
    }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\ui\WebViewBackend.java`
```java
package com.express.servermanagertool.ui;

import com.express.servermanagertool.core.TerminalSession;
import com.express.servermanagertool.model.FileInfo;
import javafx.application.Platform;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.Base64;
import java.util.List;

public class WebViewBackend {
    private final TerminalSession session;
    private final WebView webView;
    private JSObject jsWindow;
    // 在 WebViewBackend 类中添加
    private Runnable onExitCallback;

    public void setOnExitCallback(Runnable callback) {
        this.onExitCallback = callback;
    }

    public void exitToTerminal() {
        if (onExitCallback != null) {
            Platform.runLater(onExitCallback);
        }
    }
    public WebViewBackend(TerminalSession session, WebView webView) {
        this.session = session;
        this.webView = webView;
    }

    public void setJsWindow(JSObject jsWindow) {
        this.jsWindow = jsWindow;
    }

    // ========== 终端 ==========
    public void sendCommand(String cmd) {
        try {
            session.sendCommand(cmd);
        } catch (Exception e) {
            callJs("onTerminalError", e.getMessage());
        }
    }

    public void startTerminalReader() {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(session.getInputStream()))) {
                char[] buf = new char[8192];
                int len;
                while (session.isConnected() && (len = reader.read(buf)) != -1) {
                    String chunk = new String(buf, 0, len);
                    String escaped = escapeJsString(chunk);
                    Platform.runLater(() -> callJs("onTerminalOutput", escaped));
                }
            } catch (Exception e) {
                Platform.runLater(() -> callJs("onTerminalError", "连接中断: " + e.getMessage()));
            }
        }).start();
    }

    // ========== 文件管理器 ==========
    public String listDirectory(String path) {
        try {
            List<FileInfo> files = session.listFiles(path);
            StringBuilder sb = new StringBuilder("[");
            for (FileInfo f : files) {
                sb.append("{")
                        .append("\"name\":\"").append(escapeJson(f.getName())).append("\",")
                        .append("\"fullPath\":\"").append(escapeJson(f.getFullPath())).append("\",")
                        .append("\"isDirectory\":").append(f.isDirectory()).append(",")
                        .append("\"size\":").append(f.getSize()).append(",")
                        .append("\"mtime\":\"").append(escapeJson(f.getMtime())).append("\",")
                        .append("\"permissions\":\"").append(escapeJson(f.getPermissions())).append("\"")
                        .append("},");
            }
            if (sb.length() > 1) sb.setLength(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        } catch (Exception e) {
            return "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}";
        }
    }

    public void createDirectory(String path) {
        try {
            session.createDirectory(path);
            callJs("onFileOperationComplete", "mkdir", path);
        } catch (Exception e) {
            callJs("onError", "创建目录失败: " + e.getMessage());
        }
    }

    public void deleteFile(String fullPath) {
        try {
            session.delete(fullPath);
            callJs("onFileOperationComplete", "delete", fullPath);
        } catch (Exception e) {
            callJs("onError", "删除失败: " + e.getMessage());
        }
    }

    public void uploadFile(String fileName, String base64Data, String remoteDir) {
        new Thread(() -> {
            try {
                byte[] bytes = Base64.getDecoder().decode(base64Data);
                File temp = File.createTempFile("upload", ".tmp");
                try (FileOutputStream fos = new FileOutputStream(temp)) {
                    fos.write(bytes);
                }
                session.uploadFile(temp, remoteDir);
                temp.delete();
                Platform.runLater(() -> callJs("onUploadComplete", fileName));
            } catch (Exception e) {
                Platform.runLater(() -> callJs("onError", "上传失败: " + e.getMessage()));
            }
        }).start();
    }

    public void downloadFile(String remotePath, String fileName) {
        new Thread(() -> {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                session.downloadFile(remotePath, baos);
                String b64 = Base64.getEncoder().encodeToString(baos.toByteArray());
                Platform.runLater(() -> callJs("onDownloadReady", remotePath, fileName, b64));
            } catch (Exception e) {
                Platform.runLater(() -> callJs("onError", "下载失败: " + e.getMessage()));
            }
        }).start();
    }

    // ========== 辅助 ==========
    private void callJs(String functionName, Object... args) {
        Platform.runLater(() -> {
            if (jsWindow != null) {
                StringBuilder sb = new StringBuilder(functionName);
                sb.append("(");
                for (int i = 0; i < args.length; i++) {
                    if (i > 0) sb.append(",");
                    Object arg = args[i];
                    if (arg instanceof String) {
                        sb.append("'").append(escapeJsString((String) arg)).append("'");
                    } else {
                        sb.append(arg);
                    }
                }
                sb.append(")");
                jsWindow.eval(sb.toString());
            }
        });
    }

    public String readFileContent(String remotePath) {
        System.out.println(55656);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            session.downloadFile(remotePath, baos);
            byte[] bytes = baos.toByteArray();

            // 简单文本检测（连续空字节或异常控制字符视为二进制）
            boolean isText = true;
            int nullCount = 0;
            for (int i = 0; i < Math.min(bytes.length, 2048); i++) {
                byte b = bytes[i];
                if (b == 0) {
                    nullCount++;
                    if (nullCount > 10) {
                        isText = false;
                        break;
                    }
                } else if (b < 32 && b != 9 && b != 10 && b != 13 && b != 12) {
                    isText = false;
                    break;
                }
            }

            if (!isText) {
                return "ERROR:二进制文件，无法预览";
            }

            String content = new String(bytes, java.nio.charset.StandardCharsets.UTF_8);
            // 直接返回内容，不封装JSON，避免转义问题
            return "SUCCESS:" + content;
        } catch (Exception e) {
            return "ERROR:" + e.getMessage();
        }
    }

    // 复用已有的 escapeJson 方法
    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }

    private String escapeJsString(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r");
    }
}
```

### 文件路径: `src\main\java\com\express\servermanagertool\ui\WebViewFileBrowser.java`
```java
package com.express.servermanagertool.ui;

import com.express.servermanagertool.core.TerminalSession;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebViewFileBrowser extends BorderPane {
    private static final Logger LOGGER = Logger.getLogger(WebViewFileBrowser.class.getName());

    private final TerminalSession session;
    private final Runnable onExit;          // 返回到终端的回调
    private WebView webView;
    private WebViewBackend backend;
    private boolean isBackendInjected = false;  // 防止重复注入

    public WebViewFileBrowser(TerminalSession session, Runnable onExit) {
        if (session == null) {
            throw new IllegalArgumentException("TerminalSession cannot be null");
        }
        this.session = session;
        this.onExit = onExit;
        initWebView();
    }

    private void initWebView() {
        webView = new WebView();
        webView.getEngine().setJavaScriptEnabled(true);

        // 1. 检查 HTML 文件是否存在
        String htmlPath = getClass().getResource("/templates/linux_desktop.html").toExternalForm();
        if (htmlPath == null) {
            LOGGER.severe("无法找到 /templates/linux_desktop.html，请检查资源文件");
            if (onExit != null) {
                Platform.runLater(onExit);
            }
            return;
        }
        webView.getEngine().load(htmlPath);

        // 2. 监听页面加载成功
        webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED && !isBackendInjected) {
                isBackendInjected = true;
                try {
                    // 3. 创建后端桥接
                    backend = new WebViewBackend(session, webView);
                    backend.setOnExitCallback(onExit);
                    JSObject window = (JSObject) webView.getEngine().executeScript("window");
                    window.setMember("javaBackend", backend);
                    backend.setJsWindow(window);

                    // 4. 启动终端输出推送线程
                    backend.startTerminalReader();

                    // 5. 通知前端后端已就绪（捕获前端未定义函数的异常）
                    try {
                        window.call("onJavaBackendReady");
                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "前端未定义 onJavaBackendReady 函数", e);
                    }

                    // 6. 注册退出回调（显式创建 Runnable 对象，避免 lambda 编译歧义）
                    Runnable exitCallback = () -> {
                        if (onExit != null) {
                            Platform.runLater(onExit);
                        }
                    };
                    window.setMember("exitToTerminal", exitCallback);

                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "WebViewBackend 初始化失败", e);
                    if (onExit != null) {
                        Platform.runLater(onExit);
                    }
                }
            }
        });

        // 7. 处理加载失败（如网络错误、文件不存在等）
        webView.getEngine().getLoadWorker().exceptionProperty().addListener((obs, oldErr, newErr) -> {
            if (newErr != null) {
                LOGGER.log(Level.SEVERE, "WebView 加载失败", newErr);
                if (onExit != null) {
                    Platform.runLater(onExit);
                }
            }
        });

        setCenter(webView);
    }

    /**
     * 可选：释放资源（需要在父容器移除时主动调用）
     * 由于 setVisible 是 final 方法，不能重写，请手动调用此方法或通过外置逻辑调用。
     */
    public void dispose() {
        if (backend != null) {
            try {
                // 如果 WebViewBackend 实现了 stop 方法，可以在这里调用
                // backend.stop();
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "停止后端时出错", e);
            }
            backend = null;
        }
        if (webView != null) {
            webView.getEngine().load(null); // 释放页面资源
            webView = null;
        }
        isBackendInjected = false;
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

### 文件路径: `src\main\resources\templates\linux_desktop.html`
```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>Linux 可视化桌面 - 仿 Windows 风格</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; user-select: none; }
        body { width: 100vw; height: 100vh; overflow: hidden; font-family: 'Segoe UI', 'Ubuntu', sans-serif; }
        .desktop { position: relative; width: 100%; height: 100%; background: radial-gradient(circle at 20% 30%, #1a2a3a, #0b1219); overflow: hidden; }
        .desktop-icons { position: absolute; top: 20px; left: 20px; display: flex; flex-direction: column; gap: 24px; z-index: 10; }
        .desktop-icon { display: flex; flex-direction: column; align-items: center; width: 85px; cursor: pointer; padding: 8px 4px; border-radius: 8px; transition: background 0.2s; color: white; text-shadow: 0 1px 2px rgba(0,0,0,0.5); }
        .desktop-icon:hover { background: rgba(255,255,255,0.15); backdrop-filter: blur(4px); }
        .desktop-icon i { font-size: 40px; margin-bottom: 6px; }
        .desktop-icon span { font-size: 13px; text-align: center; font-weight: 500; }
        .taskbar { position: fixed; bottom: 0; left: 0; width: 100%; height: 48px; background: rgba(20,25,35,0.85); backdrop-filter: blur(20px); border-top: 1px solid rgba(255,255,255,0.2); display: flex; align-items: center; justify-content: space-between; padding: 0 12px; z-index: 1000; color: white; font-size: 14px; }
        .start-area { display: flex; align-items: center; gap: 8px; }
        .start-btn { background: rgba(255,255,255,0.15); border-radius: 8px; padding: 6px 16px; display: flex; align-items: center; gap: 8px; cursor: pointer; transition: all 0.2s; font-weight: 500; }
        .start-btn:hover { background: rgba(255,255,255,0.25); }
        .taskbar-tray { display: flex; align-items: center; gap: 16px; background: rgba(0,0,0,0.3); padding: 4px 12px; border-radius: 20px; }
        .clock { font-family: monospace; font-weight: 500; }
        .start-menu { position: fixed; bottom: 52px; left: 12px; width: 280px; background: rgba(28,32,44,0.92); backdrop-filter: blur(30px); border-radius: 16px; border: 1px solid rgba(255,255,255,0.2); box-shadow: 0 10px 25px rgba(0,0,0,0.3); z-index: 1100; overflow: hidden; color: white; }
        .start-header { padding: 20px 20px 12px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; align-items: center; gap: 12px; }
        .start-header i { font-size: 38px; color: #f0b37b; }
        .start-apps { padding: 12px 8px; display: flex; flex-direction: column; gap: 8px; }
        .start-app-item { display: flex; align-items: center; gap: 12px; padding: 8px 12px; border-radius: 8px; cursor: pointer; transition: background 0.2s; }
        .start-app-item:hover { background: rgba(255,255,255,0.15); }
        .hidden { display: none; }

        .window { position: absolute; min-width: 480px; min-height: 360px; background: rgba(30,34,48,0.95); backdrop-filter: blur(12px); border-radius: 12px; border: 1px solid rgba(255,255,255,0.3); box-shadow: 0 10px 30px rgba(0,0,0,0.5); display: flex; flex-direction: column; overflow: hidden; z-index: 500; color: #eee; }
        .window.active { z-index: 999; border-color: rgba(100,150,255,0.6); }
        .window-header { display: flex; align-items: center; justify-content: space-between; padding: 10px 16px; background: rgba(0,0,0,0.3); cursor: grab; border-bottom: 1px solid rgba(255,255,255,0.1); font-weight: 500; }
        .window-header:active { cursor: grabbing; }
        .window-controls { display: flex; gap: 12px; }
        .window-controls i { cursor: pointer; opacity: 0.7; }
        .window-controls i:hover { opacity: 1; color: #ff9f4a; }
        .window-content { flex: 1; padding: 16px; overflow-y: auto; background: rgba(20,24,36,0.7); font-size: 14px; }

        .file-toolbar { display: flex; gap: 8px; margin-bottom: 12px; flex-wrap: wrap; align-items: center; }
        .file-toolbar button { background: rgba(255,255,255,0.1); border: none; color: #eee; padding: 6px 12px; border-radius: 6px; cursor: pointer; font-size: 12px; transition: 0.1s; }
        .file-toolbar button:hover { background: rgba(255,255,255,0.25); }
        .file-list { list-style: none; margin: 0; padding: 0; }
        .file-list li { padding: 8px; border-bottom: 1px solid rgba(255,255,255,0.1); display: flex; align-items: center; justify-content: space-between; }
        .file-list li:hover { background: rgba(255,255,255,0.1); }
        .file-list li .file-name { display: flex; align-items: center; gap: 12px; flex: 1; }
        .file-actions button { background: transparent; border: none; color: #ccc; cursor: pointer; margin-left: 8px; }
        .file-actions button:hover { color: white; }

        .context-menu { position: fixed; background: #2d2f3a; border-radius: 6px; box-shadow: 0 4px 12px rgba(0,0,0,0.4); z-index: 2000; min-width: 160px; overflow: hidden; }
        .context-menu-item { padding: 8px 16px; cursor: pointer; color: #eee; font-size: 13px; transition: 0.1s; }
        .context-menu-item:hover { background: #0a6cff; }
        ::-webkit-scrollbar { width: 6px; }
        ::-webkit-scrollbar-track { background: #1e1e2e; }
        ::-webkit-scrollbar-thumb { background: #888; border-radius: 4px; }
    </style>
</head>
<body>
<div class="desktop" id="desktop">
    <div class="desktop-icons">
        <div class="desktop-icon" data-app="terminal"><i class="fas fa-terminal" style="color:#2ecc71"></i><span>终端</span></div>
        <div class="desktop-icon" data-app="filemanager"><i class="fas fa-folder-open" style="color:#f1c40f"></i><span>文件管理器</span></div>
    </div>
    <div class="taskbar">
        <div class="start-area"><div class="start-btn" id="startBtn"><i class="fab fa-linux"></i><span>开始</span></div></div>
        <div class="taskbar-tray"><i class="fas fa-volume-up"></i><i class="fas fa-network-wired"></i><i class="fas fa-battery-full"></i><span class="clock" id="clock">--:--</span></div>
    </div>
    <div class="start-menu hidden" id="startMenu">
        <div class="start-header"><i class="fab fa-ubuntu"></i><span>Linux 发行版</span></div>
        <div class="start-apps">
            <div class="start-app-item" data-app="terminal"><i class="fas fa-terminal"></i><span>终端</span></div>
            <div class="start-app-item" data-app="filemanager"><i class="fas fa-folder-tree"></i><span>文件管理器</span></div>
            <div class="start-app-item" id="fakePower"><i class="fas fa-power-off"></i><span>锁定</span></div>
        </div>
    </div>
</div>

<script>
    // ========== 全局变量 ==========
    const activeWindows = new Map();
    let globalZIndex = 800;
    let javaBackendReady = false;
    let currentDir = "/";

    // 时钟
    function updateClock() { const now = new Date(); document.getElementById('clock').innerText = now.toLocaleTimeString('zh-CN', { hour:'2-digit', minute:'2-digit' }); }
    setInterval(updateClock,1000); updateClock();

    // 开始菜单
    const startBtn = document.getElementById('startBtn');
    const startMenu = document.getElementById('startMenu');
    function toggleStartMenu(e) { e.stopPropagation(); startMenu.classList.toggle('hidden'); }
    startBtn.addEventListener('click', toggleStartMenu);
    document.addEventListener('click', function(e) { if(!startMenu.contains(e.target) && e.target !== startBtn && !startBtn.contains(e.target)) startMenu.classList.add('hidden'); });
    startMenu.addEventListener('click', (e) => e.stopPropagation());

    // 窗口拖拽
    function makeWindowDraggable(windowEl, headerEl) {
        let isDragging=false, offsetX,offsetY, startLeft,startTop;
        const onMouseMove=(e)=>{ if(!isDragging)return; e.preventDefault(); let newLeft=startLeft+(e.clientX-offsetX), newTop=startTop+(e.clientY-offsetY); const maxX=window.innerWidth-windowEl.offsetWidth, maxY=window.innerHeight-windowEl.offsetHeight-10; newLeft=Math.min(Math.max(newLeft,-windowEl.offsetWidth+100),Math.max(maxX,20)); newTop=Math.min(Math.max(newTop,20),Math.max(maxY,20)); windowEl.style.left=newLeft+'px'; windowEl.style.top=newTop+'px'; };
        const onMouseUp=()=>{ isDragging=false; document.removeEventListener('mousemove',onMouseMove); document.removeEventListener('mouseup',onMouseUp); };
        headerEl.addEventListener('mousedown',(e)=>{ if(e.target.closest('.window-controls'))return; e.preventDefault(); bringToFront(windowEl); isDragging=true; offsetX=e.clientX; offsetY=e.clientY; const rect=windowEl.getBoundingClientRect(); startLeft=rect.left; startTop=rect.top; document.addEventListener('mousemove',onMouseMove); document.addEventListener('mouseup',onMouseUp); });
    }
    function bringToFront(windowEl) { globalZIndex++; windowEl.style.zIndex=globalZIndex; document.querySelectorAll('.window').forEach(w=>w.classList.remove('active')); windowEl.classList.add('active'); }
    function closeWindow(appId,windowEl) { if(activeWindows.has(appId)){ windowEl.remove(); activeWindows.delete(appId); } }
    function openApp(appId,title,contentGenerator,width=540,height=400) {
        if(activeWindows.has(appId)){ bringToFront(activeWindows.get(appId)); return; }
        const win=document.createElement('div'); win.className='window'; win.style.width=width+'px'; win.style.height=height+'px';
        const left=Math.min(window.innerWidth-width-40,Math.max(80,Math.random()*200+30));
        const topVal=Math.min(window.innerHeight-height-70,Math.max(50,Math.random()*150+30));
        win.style.left=left+'px'; win.style.top=topVal+'px'; win.style.zIndex=++globalZIndex;
        const header=document.createElement('div'); header.className='window-header';
        header.innerHTML=`<div class="window-title"><i class="fas ${appId==='terminal'?'fa-terminal':'fa-folder-open'}"></i><span>${title}</span></div><div class="window-controls"><i class="fas fa-times close-window"></i></div>`;
        win.appendChild(header);
        const contentDiv=document.createElement('div'); contentDiv.className='window-content'; contentGenerator(contentDiv); win.appendChild(contentDiv);
        document.body.appendChild(win);
        const closeBtn=header.querySelector('.close-window'); closeBtn.addEventListener('click',(e)=>{ e.stopPropagation(); closeWindow(appId,win); });
        win.addEventListener('mousedown',()=>bringToFront(win)); makeWindowDraggable(win,header);
        activeWindows.set(appId,win); bringToFront(win);
    }

    // 预览模态框
    function showTextModal(title, content) {
        const modal = document.createElement('div');
        modal.style.cssText = 'position:fixed;top:0;left:0;width:100%;height:100%;background:rgba(0,0,0,0.8);display:flex;align-items:center;justify-content:center;z-index:20000;';
        const dialog = document.createElement('div');
        dialog.style.cssText = 'background:#1e1e2e;border-radius:12px;width:70%;max-width:900px;height:60%;display:flex;flex-direction:column;box-shadow:0 8px 20px black;';
        dialog.innerHTML = `
            <div style="padding:12px;background:#2d2d3a;border-radius:12px 12px 0 0;display:flex;justify-content:space-between;">
                <span><i class="fas fa-file-alt"></i> ${escapeHtml(title)}</span>
                <span style="cursor:pointer;font-weight:bold;font-size:18px;" onclick="this.closest('.modal-overlay').remove()">&times;</span>
            </div>
            <pre style="margin:0;padding:16px;overflow:auto;white-space:pre-wrap;font-family:monospace;font-size:13px;color:#ccc;flex:1;">${escapeHtml(content)}</pre>
        `;
        modal.appendChild(dialog);
        modal.classList.add('modal-overlay');
        document.body.appendChild(modal);
        modal.onclick = (e) => { if (e.target === modal) modal.remove(); };
    }

    function escapeHtml(text) {
        return text.replace(/[&<>]/g, function(m) {
            if (m === '&') return '&amp;';
            if (m === '<') return '&lt;';
            if (m === '>') return '&gt;';
            return m;
        });
    }

    // 右键菜单管理
    let currentContextMenu = null;
    function hideContextMenu() { if(currentContextMenu){ currentContextMenu.remove(); currentContextMenu=null; } }
    document.addEventListener('click', hideContextMenu);
    // 注意：不要在这里加 document.addEventListener('contextmenu', hideContextMenu)，会干扰菜单自身的右键

    // ========== 文件管理器 ==========
    function generateRealFileManagerContent(container) {
        container.innerHTML = `
            <div class="file-toolbar">
                <button id="upBtn" title="向上"><i class="fas fa-level-up-alt"></i> 向上</button>
                <button id="refreshBtn"><i class="fas fa-sync-alt"></i> 刷新</button>
                <button id="uploadBtn"><i class="fas fa-upload"></i> 上传</button>
                <button id="mkdirBtn"><i class="fas fa-folder-plus"></i> 新建文件夹</button>
                <input type="file" id="fileUploadInput" style="display:none" />
                <span id="currentPathDisplay" style="margin-left:12px; font-size:12px;">路径: /</span>
            </div>
            <ul class="file-list" id="realFileList"></ul>
        `;
        const fileListUl = container.querySelector('#realFileList');
        const pathSpan = container.querySelector('#currentPathDisplay');
        const upBtn = container.querySelector('#upBtn');
        const refreshBtn = container.querySelector('#refreshBtn');
        const uploadBtn = container.querySelector('#uploadBtn');
        const mkdirBtn = container.querySelector('#mkdirBtn');
        const fileInput = container.querySelector('#fileUploadInput');

        async function loadDir(path) {
            if (!javaBackendReady || !window.javaBackend) {
                fileListUl.innerHTML = '<li>等待后端连接...</li>';
                return;
            }
            const result = window.javaBackend.listDirectory(path);
            let files;
            try {
                files = JSON.parse(result);
                if (files.error) throw new Error(files.error);
            } catch(e) {
                fileListUl.innerHTML = `<li>加载失败: ${e.message}</li>`;
                return;
            }
            currentDir = path;
            pathSpan.innerText = `路径: ${currentDir}`;
            fileListUl.innerHTML = '';
            files.sort((a,b) => (b.isDirectory - a.isDirectory) || a.name.localeCompare(b.name));
            for (let f of files) {
                const li = document.createElement('li');
                const nameSpan = document.createElement('div');
                nameSpan.className = 'file-name';
                nameSpan.innerHTML = f.isDirectory ? `📁 ${f.name}` : `📄 ${f.name}`;
                if (f.isDirectory) {
                    nameSpan.style.cursor = 'pointer';
                    nameSpan.onclick = (e) => { e.stopPropagation(); loadDir(f.fullPath); };
                }

                const actionsDiv = document.createElement('div');
                actionsDiv.className = 'file-actions';
                if (!f.isDirectory) {
                    const dlBtn = document.createElement('button');
                    dlBtn.innerHTML = '<i class="fas fa-download"></i>';
                    dlBtn.onclick = (e) => { e.stopPropagation(); window.javaBackend.downloadFile(f.fullPath, f.name); };
                    actionsDiv.appendChild(dlBtn);
                }
                const delBtn = document.createElement('button');
                delBtn.innerHTML = '<i class="fas fa-trash"></i>';
                delBtn.onclick = (e) => { e.stopPropagation(); if(confirm(`确定删除 ${f.name} 吗？`)){ window.javaBackend.deleteFile(f.fullPath); setTimeout(()=>loadDir(currentDir),500); } };
                actionsDiv.appendChild(delBtn);
                li.appendChild(nameSpan);
                li.appendChild(actionsDiv);

                // 右键菜单（重点：增加“打开”项）
                li.addEventListener('contextmenu', (e) => {
                    e.preventDefault();
                    e.stopPropagation();
                    hideContextMenu();
                    const menu = document.createElement('div');
                    menu.className = 'context-menu';
                    menu.style.left = e.clientX + 'px';
                    menu.style.top = e.clientY + 'px';

                    // 新建文件夹
                    const newFolderItem = document.createElement('div');
                    newFolderItem.className = 'context-menu-item';
                    newFolderItem.innerHTML = '<i class="fas fa-folder-plus"></i> 新建文件夹';
                    newFolderItem.onclick = () => { const name=prompt("新建文件夹名称:"); if(name) mkdir(name); hideContextMenu(); };
                    menu.appendChild(newFolderItem);

                    // 刷新
                    const refreshItem = document.createElement('div');
                    refreshItem.className = 'context-menu-item';
                    refreshItem.innerHTML = '<i class="fas fa-sync-alt"></i> 刷新';
                    refreshItem.onclick = () => { loadDir(currentDir); hideContextMenu(); };
                    menu.appendChild(refreshItem);

                    // 如果是文件，添加“打开”菜单（cat 并弹窗显示）
                    if (!f.isDirectory) {
                        const openItem = document.createElement('div');
                        openItem.className = 'context-menu-item';
                        openItem.innerHTML = '<i class="fas fa-file-alt"></i> 打开';
                        openItem.onclick = () => {
                            try {
                                const content = window.javaBackend.readFileContent(f.fullPath);
                                if (content.startsWith("ERROR:")) {
                                    alert(content);
                                } else {
                                    showTextModal(f.name, content);
                                }
                            } catch (err) {
                                alert("打开文件失败: " + err.message);
                            }
                            hideContextMenu();
                        };
                        menu.appendChild(openItem);

                        // 删除文件
                        const deleteItem = document.createElement('div');
                        deleteItem.className = 'context-menu-item';
                        deleteItem.innerHTML = '<i class="fas fa-trash"></i> 删除';
                        deleteItem.onclick = () => {
                            if(confirm(`删除 ${f.name} ?`)){
                                window.javaBackend.deleteFile(f.fullPath);
                                setTimeout(()=>loadDir(currentDir),500);
                            }
                            hideContextMenu();
                        };
                        menu.appendChild(deleteItem);
                    } else {
                        // 目录：删除目录（空目录）
                        const deleteDirItem = document.createElement('div');
                        deleteDirItem.className = 'context-menu-item';
                        deleteDirItem.innerHTML = '<i class="fas fa-trash"></i> 删除目录';
                        deleteDirItem.onclick = () => {
                            if(confirm(`删除目录 ${f.name}？只允许删除空目录`)){
                                window.javaBackend.deleteFile(f.fullPath);
                                setTimeout(()=>loadDir(currentDir),500);
                            }
                            hideContextMenu();
                        };
                        menu.appendChild(deleteDirItem);
                    }

                    document.body.appendChild(menu);
                    currentContextMenu = menu;
                    // 点击其他地方关闭菜单（延迟添加，避免立即触发）
                    setTimeout(() => {
                        const closeHandler = () => { hideContextMenu(); document.removeEventListener('click', closeHandler); };
                        document.addEventListener('click', closeHandler);
                    }, 10);
                });
                fileListUl.appendChild(li);
            }
        }

        async function mkdir(name) {
            if(!name) return;
            const newPath = currentDir.endsWith('/') ? currentDir + name : currentDir + '/' + name;
            window.javaBackend.createDirectory(newPath);
            setTimeout(()=>loadDir(currentDir),500);
        }

        function goUp() {
            if (currentDir === '/') return;
            let parent = currentDir.substring(0, currentDir.lastIndexOf('/'));
            if (parent === '') parent = '/';
            loadDir(parent);
        }

        upBtn.onclick = goUp;
        refreshBtn.onclick = () => loadDir(currentDir);
        uploadBtn.onclick = () => fileInput.click();
        fileInput.onchange = (e) => {
            const file = e.target.files[0];
            if (!file) return;
            const reader = new FileReader();
            reader.onload = (ev) => {
                const base64 = ev.target.result.split(',')[1];
                window.javaBackend.uploadFile(file.name, base64, currentDir);
            };
            reader.readAsDataURL(file);
            fileInput.value = '';
        };
        mkdirBtn.onclick = () => { const name = prompt("输入文件夹名:"); if(name) mkdir(name); };

        window.onFileOperationComplete = (op) => { if(op==='delete'||op==='mkdir') loadDir(currentDir); };
        window.onUploadComplete = (fileName) => { alert(`上传 ${fileName} 完成`); loadDir(currentDir); };
        window.onDownloadReady = (remotePath, fileName, b64) => { const a=document.createElement('a'); a.href='data:application/octet-stream;base64,'+b64; a.download=fileName; a.click(); };
        window.onError = (msg) => alert("错误: " + msg);

        loadDir(currentDir);
    }

    // 占位终端生成器（点击桌面终端图标时退出）
    function generateRealTerminalContent(container) { container.innerHTML = '<div>终端已重定向到退出桌面</div>'; }

    // 应用入口
    function openTerminal() {
        if (window.javaBackend && typeof window.javaBackend.exitToTerminal === 'function') {
            window.javaBackend.exitToTerminal();
        } else if (window.exitToTerminal) {
            window.exitToTerminal();
        } else {
            alert("无法返回终端，请检查后端连接");
        }
    }

    function openFileManager() {
        openApp('filemanager', '文件管理器', (container) => {
            if (javaBackendReady && window.javaBackend) {
                generateRealFileManagerContent(container);
            } else {
                container.innerHTML = '<div style="padding:20px;text-align:center;">等待后端连接...</div>';
                const interval = setInterval(() => {
                    if (javaBackendReady && window.javaBackend && activeWindows.has('filemanager')) {
                        clearInterval(interval);
                        const win = activeWindows.get('filemanager');
                        const contentDiv = win.querySelector('.window-content');
                        if (contentDiv) generateRealFileManagerContent(contentDiv);
                    }
                }, 1000);
            }
        }, 650, 480);
    }

    // 后端就绪回调
    window.onJavaBackendReady = function() {
        javaBackendReady = true;
        console.log("Java backend ready");
        if (activeWindows.has('filemanager')) {
            const win = activeWindows.get('filemanager');
            const contentDiv = win.querySelector('.window-content');
            if (contentDiv && contentDiv.innerHTML.includes('等待后端连接')) {
                generateRealFileManagerContent(contentDiv);
            }
        }
    };

    // 桌面图标事件
    document.querySelectorAll('.desktop-icon').forEach(icon => {
        icon.addEventListener('dblclick', (e) => {
            e.stopPropagation();
            const app = icon.getAttribute('data-app');
            if (app === 'terminal') openTerminal();
            else if (app === 'filemanager') openFileManager();
        });
    });
    document.querySelectorAll('.start-app-item').forEach(item => {
        item.addEventListener('click', (e) => {
            const app = item.getAttribute('data-app');
            if (app === 'terminal') openTerminal();
            else if (app === 'filemanager') openFileManager();
            else if (item.id === 'fakePower') alert("锁定功能暂未实现");
            startMenu.classList.add('hidden');
        });
    });

    window.addEventListener('resize', () => {});
    document.querySelector('.desktop').addEventListener('contextmenu', (e) => { e.preventDefault(); alert("桌面右键菜单（演示）"); });
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

