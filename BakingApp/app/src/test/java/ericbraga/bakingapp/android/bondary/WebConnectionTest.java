package ericbraga.bakingapp.android.bondary;

import net.bytebuddy.asm.Advice;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ericbraga.bakingapp.bondary.Connection;

public class WebConnectionTest {

    @Test
    public void empty_Url_Should_ThrowAnException() {

        Connection.Callback callback =
                Mockito.mock(Connection.Callback.class);
        try {
            WebConnection connection = new WebConnection();
            connection.connect("", callback);
            Assert.fail("WebConnection with invalid Url should raise an Exception");

        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void null_Url_Should_ThrowAnException() {

        Connection.Callback callback =
                Mockito.mock(Connection.Callback.class);
        try {
            WebConnection connection = new WebConnection();
            connection.connect(null, callback);
            Assert.fail("WebConnection with invalid Url should raise an Exception");

        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void invalid_Url_Should_ThrowAnException() {
        WebConnection connection = new WebConnection();

        Connection.Callback callback =
                Mockito.mock(Connection.Callback.class);

        connection.connect("Some_Url", callback);

        Mockito.verify(callback, Mockito.times(1)).onError(Mockito.anyString());
    }

    @Test
    public void valid_Url_Should_Works() {
        Connection.Callback callback =
                Mockito.mock(Connection.Callback.class);

        final Object lock = new Object();

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                synchronized(lock) {
                    lock.notifyAll();
                }
                return null;
            }
        }).when(callback).onSuccess(Mockito.anyString());

        WebConnection connection = Mockito.spy(WebConnection.class);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    Assert.fail(e.getMessage());
                }

                return null;
            }
        }).when(connection).connect(Mockito.anyString(),Mockito.any(Connection.Callback.class) );
        connection.connect("http://www.google.com.br", callback);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(callback, Mockito.timeout(1000)).onSuccess(captor.capture());
    }
}