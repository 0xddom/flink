.PHONY: package

package:
	cd flinkProgram && mvn clean package -Pbuild-jar

	
