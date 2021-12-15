FROM fedora:32
LABEL Mainainer="Gregory DEPUILLE"
LABEL Name="arig-yocto-build"

# Build arg to map current run user
ARG host_uid=1001
ARG host_gid=1001

# Username inside docker image
ENV USER_NAME yocto-build

# Set a useful default locale
RUN dnf install -y glibc-langpack-en && dnf clean all -y
ENV LC_ALL=en_US.UTF-8

# Install dependencies
RUN dnf update -y && \
    dnf install -y gawk make wget tar bzip2 gzip python3 unzip perl patch \
                  diffutils diffstat git cpp gcc gcc-c++ glibc-devel texinfo \
                  chrpath ccache perl-Data-Dumper perl-Text-ParseWords \
                  perl-Thread-Queue perl-bignum socat python3-pexpect findutils \
                  which file cpio python python3-pip xz python3-GitPython \
                  python3-jinja2 SDL-devel xterm rpcgen mesa-libGL-devel \
                  hostname iproute && \
    dnf install -y qemu-system-aarch64 qemu-system-x86 && \
    dnf clean all -y

# Create group and user to run build
RUN groupadd -g $host_gid $USER_NAME && \
    useradd -g $host_gid -m -s /bin/bash -u $host_uid -G wheel $USER_NAME && \
    sed -e 's/^%wheel/#%wheel/g' -e 's/^# %wheel/%wheel/g' -i /etc/sudoers

USER $USER_NAME

# Build directory to make BSP
RUN mkdir /home/$USER_NAME/build
VOLUME /home/$USER_NAME/build

WORKDIR /home/$USER_NAME/build

CMD ["/home/yocto-build/build/build.sh"]
