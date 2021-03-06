/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vipshop.microscope.client.metric;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Representation of a metric
 * <p/>
 * {
 * "metric"   :      "sys.cpu.nice",
 * "timestamp":      1346846400,
 * "value"    :      18,
 * "tags"     :      {"host": "web01", "dc": "lga" }
 * }
 *
 * @author Xu Fei
 * @version 1.0
 */
public class MetricData implements Serializable {

    private static final long serialVersionUID = -4616838090366748782L;

    private String metric;
    private Long timestamp;
    private Object value;
    private Map<String, String> tags = new HashMap<String, String>();

    private MetricData() {
    }

    public static Builder named(String name) {
        return new Builder(name);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof MetricData)) {
            return false;
        }

        final MetricData rhs = (MetricData) o;

        return equals(metric, rhs.metric) && equals(timestamp, rhs.timestamp)
                && equals(value, rhs.value)
                && equals(tags, rhs.tags);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{metric, timestamp, value, tags});
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "->metric: " + metric + ",value: " + value + ",timestamp: " + timestamp + ",tags: " + tags;
    }

    public String getMetric() {
        return metric;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Object getValue() {
        return value;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    private boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    public static class Builder {

        private final MetricData metric;

        public Builder(String name) {
            this.metric = new MetricData();
            metric.metric = name;
        }

        public Builder withValue(Object value) {
            metric.value = value;
            return this;
        }

        public Builder withTimestamp(Long timestamp) {
            metric.timestamp = timestamp;
            return this;
        }

        public Builder withTags(Map<String, String> tags) {
            if (tags != null) {
                metric.tags.putAll(tags);
            }
            return this;
        }

        public MetricData build() {
            return metric;
        }
    }
}

