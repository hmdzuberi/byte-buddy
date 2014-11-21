package net.bytebuddy.matcher;

import net.bytebuddy.instrumentation.ByteCodeElement;
import net.bytebuddy.instrumentation.type.TypeDescription;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class VisibilityMatcherTest extends AbstractElementMatcherTest<VisibilityMatcher<?>> {

    @SuppressWarnings("unchecked")
    public VisibilityMatcherTest() {
        super((Class<? extends VisibilityMatcher<?>>) (Object) VisibilityMatcher.class, "isVisibleTo");
    }

    @Mock
    private TypeDescription typeDescription;

    @Mock
    private ByteCodeElement byteCodeElement;

    @Test
    public void testMatch() throws Exception {
        when(byteCodeElement.isVisibleTo(typeDescription)).thenReturn(true);
        assertThat(new VisibilityMatcher<ByteCodeElement>(typeDescription).matches(byteCodeElement), is(true));
        verify(byteCodeElement).isVisibleTo(typeDescription);
        verifyNoMoreInteractions(byteCodeElement);
        verifyZeroInteractions(typeDescription);
    }

    @Test
    public void testNoMatch() throws Exception {
        when(byteCodeElement.isVisibleTo(typeDescription)).thenReturn(false);
        assertThat(new VisibilityMatcher<ByteCodeElement>(typeDescription).matches(byteCodeElement), is(false));
        verify(byteCodeElement).isVisibleTo(typeDescription);
        verifyNoMoreInteractions(byteCodeElement);
        verifyZeroInteractions(typeDescription);
    }
}
