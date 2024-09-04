package buptworker.service.impl;

import buptworker.entity.MchOrder;
import buptworker.entity.MchOrderDetail;
import buptworker.entity.MchOrderItem;
import buptworker.mapper.MchOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Component
public class MchOrderService_ {

    @Autowired
    private MchOrderMapper mchOrderMapper;

    @Override
    public List<MchOrder> getMchOrders(String mchId)
    {
        List<MchOrder> mchOrders = new List<MchOrder>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<MchOrder> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(MchOrder mchOrder) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends MchOrder> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends MchOrder> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public MchOrder get(int index) {
                return null;
            }

            @Override
            public MchOrder set(int index, MchOrder element) {
                return null;
            }

            @Override
            public void add(int index, MchOrder element) {

            }

            @Override
            public MchOrder remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<MchOrder> listIterator() {
                return null;
            }

            @Override
            public ListIterator<MchOrder> listIterator(int index) {
                return null;
            }

            @Override
            public List<MchOrder> subList(int fromIndex, int toIndex) {
                return List.of();
            }
        };
        List<MchOrderDetail> mchOrderDetails =  mchOrderMapper.listMchOrderDetail(mchId);
        for (MchOrderDetail mchOrderDetail : mchOrderDetails) {
            List<MchOrderItem> mchOrderItems = mchOrderMapper.listMchOrderItem(mchOrderDetail.getOrderId());
            mchOrders.add(new MchOrder(mchOrderDetail, mchOrderItems));
        }
        return mchOrders;
    }
}
