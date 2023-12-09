/*
 * ====================================================================
 * Copyright  (c) : 2021 by Kaleris. All rights reserved.
 * ====================================================================
 *
 * The copyright to the computer software herein is the property of Kaleris
 * The software may be used and/or copied only
 * with the written permission of Kaleris or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package iit.ase.cw.platform.common.util.list;

import iit.ase.cw.platform.common.util.constant.ThaproGlobalConstant;
import iit.ase.cw.platform.common.util.toolkit.CollectionToolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class GenericThaproList<E> implements Serializable {

    @Builder.Default
    private List<E> list = new ArrayList<>();

    public GenericThaproList() {
    }

    public static <E extends Serializable> GenericThaproList<E> getEmptyList() {
        GenericThaproList<E> emptyModel = new GenericThaproList<>();
        emptyModel.setList(Collections.emptyList());
        return emptyModel;
    }

    public List<E> getList() {
        if (this.list == null) {
            list = new ArrayList<>();
        }
        return this.list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public Stream<E> getStream() {
        return getList().stream();
    }

    public E get(int index) {
        if (size() > index) {
            return getList().get(index);
        }
        return null;
    }

    public void add(E e) {
        getList().add(e);
    }

    public void add(E... types) {
        for (E type : types) {
            add(type);
        }
    }

    public void addList(List<E> list) {
        if (list != null) {
            getList().addAll(list);
        }
    }

    public void addAll(List<? extends E> list) {
        getList().addAll(list);
    }

    public void addAll(GenericThaproList<E> list) {
        getList().addAll(list.getList());
    }

    public void remove(E e) {
        if (e == null) {
            return;
        }
        getList().remove(e);
    }

    public void removeIf(Predicate<E> predicate) {
        Iterator<E> iterator = this.list.iterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (predicate.test(next)) {
                getList().remove(next);
            }
        }
    }

    public List<E> sort(Comparator<E> comparator) {
        Collections.sort(getList(), comparator);
        return getList();
    }

    public void removeAll() {
        getList().clear();
    }

    public boolean isEmpty() {
        return getList().isEmpty();
    }

    public int size() {
        return getList().size();
    }

    public int indexOf(E e) {
        return getList().indexOf(e);
    }

    public boolean contains(E e) {
        return getList().contains(e);
    }

    public E getFirstElement() {
        return CollectionToolkit.getFirstElement(list);
    }

    public void clear() {
        getList().clear();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("list", StringUtils.join(list, ThaproGlobalConstant.Symbol.SEMI_COLON))
            .toString();
    }
}
