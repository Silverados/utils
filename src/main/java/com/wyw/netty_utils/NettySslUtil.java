package com.wyw.netty_utils;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.io.File;
import java.security.cert.CertificateException;

public class NettySslUtil {

    public static final boolean SSL = System.getProperty("ssl") != null;

    private NettySslUtil() {
    }

    public static SslContext buildSslClientContext() throws SSLException {
        if (!SSL) {
            return null;
        }
        return SslContextBuilder.forClient().build();
    }

    public static SslContext buildSslServerContext() throws CertificateException, SSLException {
        if (!SSL) {
            return null;
        }
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        return SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
    }

    public static SslContext buildOpenSslServerContext(File certificate, File privateKey) throws SSLException {
        if (!SSL) {
            return null;
        }

        return SslContextBuilder.forServer(certificate, privateKey).sslProvider(SslProvider.OPENSSL).build();
    }
}
