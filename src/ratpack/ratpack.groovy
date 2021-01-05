import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

import ratpack.groovy.template.MarkupTemplateModule

ratpack {
    bindings {
        module MarkupTemplateModule
    }

    handlers {
        get {
            render groovyMarkupTemplate("index.gtpl", title: "Sneaky Sneaky Dinosaurs")
        }
    }
}
