/*
 * Copyright 1999-2101 kingdee Group.
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
package com.powerbridge.bssp.common.util.json;

import java.io.IOException;

/**
 * Beans that support customized output of JSON text to a writer shall implement this interface.
*  @author simon.xie
* @date 2017-4-29 下午1:25:40
*/
public interface JSONStreamAware {

    /**
     * write JSON string to out.
     */
    void writeJSONString(Appendable out) throws IOException;
}
