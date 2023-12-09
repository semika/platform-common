package iit.ase.cw.platform.common.type;

import iit.ase.cw.platform.common.constant.ThaproGlobalConstant;
import java.util.List;

public final class ThaproResponseBuilder {

    public ThaproResponseBuilder() {
    }

    public static <E> MessagePhrase<E> create() {
        ThaproResponse<E> thaproResponse = new ThaproResponse<>();
        return new MessagePhrase(thaproResponse);
    }

    public static class MessagePhrase<E> {

        private final ThaproResponse<E> thaproResponse;

        public MessagePhrase(ThaproResponse<E> thaproResponse) {
            this.thaproResponse = thaproResponse;
        }

        public DataPhrase<E> withMessage(String message) {
            this.thaproResponse.setMessage(message);
            return new DataPhrase<>(this.thaproResponse);
        }

        public DataPhrase<E> withoutMessage() {
            this.thaproResponse.setMessage(ThaproGlobalConstant.Symbol.EMPTY_STR);
            return new DataPhrase<>(this.thaproResponse);
        }
    }

    public static class DataPhrase<E> {

        private final ThaproResponse<E> thaproResponse;

        public DataPhrase(ThaproResponse<E> thaproResponse) {
            this.thaproResponse = thaproResponse;
        }

        public PagePhrase<E> withData(E e) {
            this.thaproResponse.setData(e);
            return new PagePhrase<>(this.thaproResponse);
        }

        public PagePhrase<E> withData(List<E> list) {
            this.thaproResponse.setDataList(list);
            return new PagePhrase<>(this.thaproResponse);
        }

        public PagePhrase<E> withoutData() {
            return new PagePhrase<>(this.thaproResponse);
        }
    }

    public static class PagePhrase<E> {

        private final ThaproResponse<E> thaproResponse;

        public PagePhrase(ThaproResponse<E> thaproResponse) {
            this.thaproResponse = thaproResponse;
        }

        public ThaproResponse<E> withPageInfo(ThaproPage page) {
            this.thaproResponse.setPagination(page);
            return this.thaproResponse;
        }

        public ThaproResponse<E> withoutPageInfo() {
            return this.thaproResponse;
        }
    }

}
