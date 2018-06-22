package ericbraga.bakingapp.environment.boundary;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ericbraga.bakingapp.environment.boundary.common.WebConnection;
import ericbraga.bakingapp.environment.boundary.common.interfaces.Connection;

public class WebConnectionTest {

    @Test
    public void empty_Url_Should_ThrowAnException() {

        Connection.Callback callback =
                Mockito.mock(Connection.Callback.class);
        try {
            WebConnection connection = new WebConnection("");
            connection.connect(callback);
            Assert.fail("WebConnection with invalid Url should raise an Exception");

        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void null_Url_Should_ThrowAnException() {

        Connection.Callback callback =
                Mockito.mock(Connection.Callback.class);
        try {
            WebConnection connection = new WebConnection(null);
            connection.connect(callback);
            Assert.fail("WebConnection with invalid Url should raise an Exception");

        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void invalid_Url_Should_ThrowAnException() {
        WebConnection connection = new WebConnection("Some_Url");

        Connection.Callback callback =
                Mockito.mock(Connection.Callback.class);

        connection.connect(callback);

        Mockito.verify(callback, Mockito.times(1)).onError(Mockito.anyString());
    }

    @Test
    public void valid_Url_Should_Works() {
        final Connection.Callback callback =
                Mockito.mock(Connection.Callback.class);

        final Object lock = new Object();

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                synchronized(lock) {
                    lock.notifyAll();
                }
                return invocation.getArgument(0);
            }
        }).when(callback).onSuccess(Mockito.anyString());

        final WebConnection connection = new WebConnection("http://www.google.com.br") {
            @Override
            public void connect(Callback callback) {
                super.connect(callback);
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Assert.fail(e.getMessage());
                    }
                }
            }
        };

        connection.connect(callback);

        synchronized (lock) {
            ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
            Mockito.verify(callback).onSuccess(captor.capture());
            System.out.println(captor.capture());
        }
    }
}