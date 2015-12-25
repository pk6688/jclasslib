/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/
package org.gjt.jclasslib.structures.attributes

import org.gjt.jclasslib.structures.AttributeInfo
import org.gjt.jclasslib.structures.InvalidByteCodeException

import java.io.DataInput
import java.io.DataOutput
import java.io.IOException

/**
 * Describes an  EnclosingMethod attribute structure.

 * @author [Vitor Carreira](mailto:vitor.carreira@gmail.com)
 */
class EnclosingMethodAttribute : AttributeInfo() {

    /**
     * Constant pool index of the CONSTANT_Class_info
     * structure representing the innermost class that encloses the
     * declaration of the current class.
     */
    var classInfoIndex: Int = 0

    /**
     * Constant pool index of the CONSTANT_NameAndType_info
     * structure representing the name and type of a method in the class
     * referenced by the class info index above.
     */
    var methodInfoIndex: Int = 0

    @Throws(InvalidByteCodeException::class, IOException::class)
    override fun read(input: DataInput) {
        classInfoIndex = input.readUnsignedShort()
        methodInfoIndex = input.readUnsignedShort()

        if (isDebug) debug("read")
    }

    @Throws(InvalidByteCodeException::class, IOException::class)
    override fun write(output: DataOutput) {
        output.writeShort(classInfoIndex)
        output.writeShort(methodInfoIndex)

        if (isDebug) debug("wrote")
    }

    override fun getAttributeLength(): Int = 4

    override fun debug(message: String) {
        super.debug("$message EnclosingMethod attribute with class index $classInfoIndex and method index $methodInfoIndex")
    }

    companion object {
        /**
         * Name of the attribute as in the corresponding constant pool entry.
         */
        val ATTRIBUTE_NAME = "EnclosingMethod"
    }
}
